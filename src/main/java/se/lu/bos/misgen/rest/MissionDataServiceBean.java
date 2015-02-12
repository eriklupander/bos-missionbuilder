package se.lu.bos.misgen.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
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
import se.lu.bos.misgen.groups.ClientAirfieldParser;
import se.lu.bos.misgen.model.GeneratedMission;
import se.lu.bos.misgen.model.PlaneType;
import se.lu.bos.misgen.model.StaticObjectType;
import se.lu.bos.misgen.model.VehicleType;
import se.lu.bos.misgen.nosql.ElasticSearchServer;
import se.lu.bos.misgen.serializer.LoadoutFactory;
import se.lu.bos.misgen.serializer.MissionConverter;
import se.lu.bos.misgen.serializer.MissionFileWriter;
import se.lu.bos.misgen.serializer.MissionWriter;
import se.lu.bos.misgen.webmodel.ActionType;
import se.lu.bos.misgen.webmodel.ClientAirfield;
import se.lu.bos.misgen.webmodel.ClientMission;
import se.lu.bos.misgen.webmodel.FormationType;

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

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    ElasticSearchServer elasticSearchServer;

    @RequestMapping(method = RequestMethod.GET, value="/mission/{serverId}/downloadmission", produces = "application/octet-stream")
    public void downloadMission(@PathVariable String serverId, HttpServletResponse response) {

        String json = elasticSearchServer.getClient().prepareGet("missions", "mission", serverId).execute().actionGet().getSourceAsString();
        try {
            ClientMission clientMission = mapper.readValue(json, ClientMission.class);

            GeneratedMission generatedMission = new MissionConverter().convert(clientMission);
            String missionFileBody = new MissionWriter().generateMission(generatedMission);

//            org.apache.commons.io.IOUtils.write(missionFileBody, response.getOutputStream());
//
            //response.setContentType("application/force-download");
//            response.setHeader("Content-Disposition", "attachment; filename=" + clientMission.getName().replaceAll(" ", "") + ".Mission");
//            response.flushBuffer();
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
            GeneratedMission generatedMission = new MissionConverter().convert(clientMission);

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

            GeneratedMission generatedMission = new MissionConverter().convert(clientMission);
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

            GeneratedMission generatedMission = new MissionConverter().convert(clientMission);
            String missionFileBody = new MissionWriter().generateMission(generatedMission);
            new MissionFileWriter().write(clientMission.getName(), generatedMission.getLocalization(), missionFileBody);

            return new ResponseEntity(missionFileBody, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/mission", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ClientMission> createClientMission(@RequestBody ClientMission clientMission) {
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

    @RequestMapping(method = RequestMethod.PUT, value = "/mission/{serverMissionId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ClientMission> updateClientMission(@PathVariable String serverMissionId, @RequestBody ClientMission clientMission) {
        String json = null;
        try {
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

//    @RequestMapping(method = RequestMethod.POST, value = "/unitGroup", consumes = "application/json", produces = "application/json")
//    public ResponseEntity<UnitGroup> getClientUnitGroup(@RequestBody UnitGroup clientUnitGroup) {
//        String json = null;
//        try {
//            json = mapper.writeValueAsString(clientUnitGroup);
//            IndexResponse response = elasticSearchServer.getClient().prepareIndex("unitGroups", "unitgroup")
//                    .setSource(json)
//                    .execute()
//                    .actionGet();
//            clientUnitGroup.setServerId(response.getId());
//            return new ResponseEntity(clientUnitGroup, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @RequestMapping(method = RequestMethod.GET, value = "/actionTypes", produces = "application/json")
    public ResponseEntity<List<ActionType>> getActionTypes() {
        return new ResponseEntity(ActionType.values(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/mission/{serverId}", produces = "application/json")
    public ResponseEntity<ClientMission> getClientMission(@PathVariable String serverId) {
        try {
            String json = elasticSearchServer.getClient().prepareGet("missions", "mission", serverId).execute().actionGet().getSourceAsString();
            ClientMission clientMission = mapper.readValue(json, ClientMission.class);
            return new ResponseEntity(clientMission, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
                            cm.setServerId(hit.id());
                            return cm;
                        }).collect(Collectors.toList());
                esData.addAll(collect);

                i++;
            }

            return new ResponseEntity(esData, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
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


    @RequestMapping(method = RequestMethod.GET, value = "/formationTypes", produces = "application/json")
    public ResponseEntity<List<FormationType>> getFormationTypes() {
        return new ResponseEntity(FormationType.values(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/airfields", produces = "application/json")
    public ResponseEntity<List<FormationType>> getAirfields() throws IOException {
        List<ClientAirfield> airfields = ClientAirfieldParser.build();

        return new ResponseEntity(airfields, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/loadouts/{planeType}", produces = "application/json")
    public ResponseEntity<List<FormationType>> getLoadouts(@PathVariable String planeType) throws IOException {
        PlaneType type = PlaneType.valueOf(planeType);
        return new ResponseEntity(LoadoutFactory.getLoadout(type), HttpStatus.OK);
    }
}
