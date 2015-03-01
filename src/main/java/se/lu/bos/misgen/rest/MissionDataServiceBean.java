package se.lu.bos.misgen.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.deletebyquery.DeleteByQueryResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lu.bos.misgen.factory.LoadoutFactory;
import se.lu.bos.misgen.groups.ClientAirfieldParser;
import se.lu.bos.misgen.model.*;
import se.lu.bos.misgen.nosql.ElasticSearchServer;
import se.lu.bos.misgen.serializer.MissionConverter;
import se.lu.bos.misgen.serializer.MissionFileWriter;
import se.lu.bos.misgen.serializer.MissionWriter;
import se.lu.bos.misgen.serializer.skin.SkinManager;
import se.lu.bos.misgen.util.EnvUtil;
import se.lu.bos.misgen.webmodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@RestController
@RequestMapping("/rest/missionbuilder")
public class MissionDataServiceBean {

    private final static Logger log = LoggerFactory.getLogger(MissionDataServiceBean.class);

    public static final String DEFAULT_DATA_DIR = "H:\\skyrim\\SteamApps\\common\\IL-2 Sturmovik Battle of Stalingrad\\data\\";

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    MissionFileWriter missionFileWriter;

    @Autowired
    ElasticSearchServer elasticSearchServer;

    @Autowired
    MissionConverter missionConverter;

    @Autowired
    ClientAirfieldParser clientAirfieldParser;

    @Autowired
    SkinManager skinManager;

    @Autowired
    EnvUtil envUtil;

    @RequestMapping(method = RequestMethod.GET, value="/mission/{serverId}/downloadmission", produces = "application/octet-stream")
    public void downloadMission(@PathVariable String serverId, HttpServletResponse response) {

        String json = elasticSearchServer.getClient().prepareGet("missions", "mission", serverId).execute().actionGet().getSourceAsString();
        try {
            ClientMission clientMission = mapper.readValue(json, ClientMission.class);

            GeneratedMission generatedMission = missionConverter.convert(clientMission);
            String missionFileBody = new MissionWriter().generateMission(generatedMission);

            IOUtils.write(missionFileBody,
                    response.getOutputStream()
            );

            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=" + clientMission.getName().replaceAll(" ", "") + ".Mission");
            response.flushBuffer();
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException("IOError writing file to output stream");
        }
    }

    @RequestMapping(method = RequestMethod.GET, value="/mission/{serverId}/downloadlocalization", produces = "application/octet-stream")
    public void downloadLocalization(@PathVariable String serverId, HttpServletResponse response) {
        String json = elasticSearchServer.getClient().prepareGet("missions", "mission", serverId).execute().actionGet().getSourceAsString();
        try {
            ClientMission clientMission = mapper.readValue(json, ClientMission.class);
            GeneratedMission generatedMission = missionConverter.convert(clientMission);

            StringBuilder buf = new StringBuilder();
            generatedMission.getLocalization().entrySet().stream().forEach(entry -> buf.append(entry.getKey()).append(":").append(entry.getValue() != null ? entry.getValue() : "").append("\r\n"));
            IOUtils.write(buf.toString(),
                    response.getOutputStream()
                    , "UTF-16LE"
            );

            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=" + clientMission.getName().replaceAll(" ", "") + ".eng");
            response.flushBuffer();
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException("IOError writing file to output stream");
        }
    }

    @RequestMapping(method = RequestMethod.GET, value="/mission/{serverId}/export", produces="text/plain")
    public ResponseEntity<String> exportMissionAsText(@PathVariable String serverId) {
        String json = elasticSearchServer.getClient().prepareGet("missions", "mission", serverId).execute().actionGet().getSourceAsString();
        try {
            ClientMission clientMission = mapper.readValue(json, ClientMission.class);

            GeneratedMission generatedMission = missionConverter.convert(clientMission);
            String missionFileBody = new MissionWriter().generateMission(generatedMission);
            return new ResponseEntity(missionFileBody, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value="/mission/{serverId}/export", produces="text/plain")
    public ResponseEntity<String> exportMissionToDisk(@PathVariable String serverId) {
        String json = elasticSearchServer.getClient().prepareGet("missions", "mission", serverId).execute().actionGet().getSourceAsString();
        try {
            ClientMission clientMission = mapper.readValue(json, ClientMission.class);

            GeneratedMission generatedMission = missionConverter.convert(clientMission);
            String missionFileBody = new MissionWriter().generateMission(generatedMission);
            String path = envUtil.getBasePath()  + "Missions\\webmissions";
            if(!path.endsWith("/")) {
                path = path + "/";
            }
            missionFileWriter.write(clientMission.getName(), generatedMission.getLocalization(), missionFileBody, path);

            return new ResponseEntity(path, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/mission", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ClientMission> createClientMission(@RequestBody ClientMission clientMission) {
        addDefaultWindlayers(clientMission);

        String json = null;
        try {
            json = mapper.writeValueAsString(clientMission);
            IndexResponse response = elasticSearchServer.getClient().prepareIndex("missions", "mission")
                    .setSource(json)
                    .execute()
                    .actionGet();
            clientMission.setServerId(response.getId());
            return new ResponseEntity(clientMission, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void addDefaultWindlayers(ClientMission clientMission) {
        clientMission.getWeather().getWindLayers().add(new WindLayer(0, 275, 2f));
        clientMission.getWeather().getWindLayers().add(new WindLayer(500, 275, 2f));
        clientMission.getWeather().getWindLayers().add(new WindLayer(1000, 275, 2f));
        clientMission.getWeather().getWindLayers().add(new WindLayer(2000, 275, 2f));
        clientMission.getWeather().getWindLayers().add(new WindLayer(5000, 275, 2f));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/mission/{serverMissionId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ClientMission> updateClientMission(@PathVariable String serverMissionId, @RequestBody ClientMission clientMission) {
        String json = null;
        try {

            sanitizeClientMission(clientMission);

            json = mapper.writeValueAsString(clientMission);
            IndexResponse response = elasticSearchServer.getClient().prepareIndex("missions", "mission", serverMissionId)
                    .setSource(json)
                    .execute()
                    .actionGet();
            clientMission.setServerId(response.getId());
            return new ResponseEntity(clientMission, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Clean some stuff up if necessary. For example, all AIR_GROUPs having one waypoint and starting airborne
     * shall have a heading directly towards waypoint 1. This is to avoid excessive manuevers on spawn resulting in crashes.
     *
     * @param clientMission
     */
    private void sanitizeClientMission(ClientMission clientMission) {

    }





    @RequestMapping(method = RequestMethod.GET, value = "/actionTypes", produces = "application/json")
    public ResponseEntity<List<ActionType>> getActionTypes() {
        return new ResponseEntity(ActionType.values(), HttpStatus.OK);
    }

    // TODO change path
    @RequestMapping(method = RequestMethod.GET, value = "/mission/{serverId}", produces = "application/json")
    public ResponseEntity<ClientMission> getClientMission(@PathVariable String serverId) {
        try {
            String json = elasticSearchServer.getClient().prepareGet("missions", "mission", serverId).execute().actionGet().getSourceAsString();
            ClientMission clientMission = mapper.readValue(json, ClientMission.class);
            if(clientMission.getWeather().getWindLayers() == null || clientMission.getWeather().getWindLayers().size() == 0) {
                addDefaultWindlayers(clientMission);
            }
            return new ResponseEntity(clientMission, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @RequestMapping(method = RequestMethod.DELETE, value = "/missions/{serverId}", produces = "text/plain")
    public ResponseEntity<String> getDeleteClientMission(@PathVariable String serverId) {
        DeleteResponse deleteResponse = elasticSearchServer.getClient().prepareDelete("missions", "mission", serverId)
                .execute()
                .actionGet();
        log.info("Delete request result: " + deleteResponse.isFound());
        return new ResponseEntity<String>("Mission identified by " + serverId + " deleted", HttpStatus.OK);
    }





    @RequestMapping(method = RequestMethod.DELETE, value = "/missions", produces = "application/json")
    public ResponseEntity<String> getDeleteClientMissions() {
        DeleteByQueryResponse response = elasticSearchServer.getClient().prepareDeleteByQuery("missions")
                .setTypes("mission")
                .setQuery(QueryBuilders.matchAllQuery())
                .execute()
                .actionGet();
        return new ResponseEntity<String>("All items deleted", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/missions", produces = "application/json")
    public ResponseEntity<List<ClientMission>> getClientMissions() {
        try {
            int scrollSize = 1000;
            List<ClientMission> esData = new ArrayList<>();
            SearchResponse response = null;
            int i = 0;
            while( response == null || response.getHits().hits().length != 0){
                response = elasticSearchServer.getClient().prepareSearch("missions")
                        .setTypes("mission")
                        .setQuery(QueryBuilders.matchAllQuery())
                        .setSize(scrollSize)
                        .setFrom(i * scrollSize)
                        .execute()
                        .actionGet();

                JsonHelper jh = new JsonHelper();
                List<ClientMission> collect = Arrays.asList(response.getHits().getHits()).stream()
                        .map(hit -> {
                            ClientMission cm = jh.apply(hit.getSourceAsString());
                            //cm.setServerId(hit.id());
                            return cm;
                        }).collect(Collectors.toList());
                esData.addAll(collect);

                i++;
            }

            return new ResponseEntity(esData, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(new ArrayList<>(), HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/planeTypes", produces = "application/json")
    public ResponseEntity<List<PlaneType>> getPlaneTypes() {
        return new ResponseEntity(PlaneType.values(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/planeTypes/{countryCode}", produces = "application/json")
    public ResponseEntity<List<PlaneType>> getPlaneTypesForSide(@PathVariable Integer countryCode) {
        return new ResponseEntity(Arrays.asList(PlaneType.values()).stream()
                .filter(pt -> pt.getCountry() == countryCode)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/vehicleTypes", produces = "application/json")
    public ResponseEntity<List<VehicleType>> getVehicleTypes() {
        return new ResponseEntity(VehicleType.values(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/vehicleTypes/{countryCode}", produces = "application/json")
    public ResponseEntity<List<VehicleType>> getVehicleTypes(@PathVariable Integer countryCode) {
        return new ResponseEntity(Arrays.asList(VehicleType.values()).stream()
                .filter(pt -> pt.getCountry() == countryCode)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/staticObjectTypes", produces = "application/json")
    public ResponseEntity<List<StaticObjectType>> getStaticObjectTypes() {
        return new ResponseEntity(StaticObjectType.values(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/staticObjectTypes/{countryCode}", produces = "application/json")
    public ResponseEntity<List<StaticObjectType>> getStaticObjectTypes(@PathVariable Integer countryCode) {
        return new ResponseEntity(Arrays.asList(StaticObjectType.values()).stream()
                .filter(pt -> pt.getCountry() == countryCode || pt.getCountry() == 0)
                .collect(Collectors.toList()), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/formationTypes/{groupType}", produces = "application/json")
    public ResponseEntity<List<FormationType>> getFormationTypes(@PathVariable String groupType) {
        GroupType groupTypeEnum = GroupType.valueOf(groupType);
        return new ResponseEntity(Arrays.asList(FormationType.values()).stream().filter(ft -> {
            int category = groupTypeEnum == GroupType.AIR_GROUP ? 0 : 1;
            return ft.getCategory() == category;
        }).collect(Collectors.toList()), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/airfields", produces = "application/json")
    public ResponseEntity<List<FormationType>> getAirfields() throws IOException {
        List<ClientAirfield> airfields = clientAirfieldParser.build();

        return new ResponseEntity(airfields, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/loadouts/{planeType}", produces = "application/json")
    public ResponseEntity<List<FormationType>> getLoadouts(@PathVariable String planeType) throws IOException {
        PlaneType type = PlaneType.valueOf(planeType);
        return new ResponseEntity(LoadoutFactory.getLoadout(type), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/skins/{planeType}", produces = "application/json")
    public ResponseEntity<List<String>> getSkins(@PathVariable String planeType) throws IOException {
        PlaneType type = PlaneType.valueOf(planeType);
        return new ResponseEntity(skinManager.getSkins(type), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/skins", produces = "application/json")
    public ResponseEntity<List<String>> getSkins() throws IOException {
        return new ResponseEntity(skinManager.getSkins(), HttpStatus.OK);
    }
}
