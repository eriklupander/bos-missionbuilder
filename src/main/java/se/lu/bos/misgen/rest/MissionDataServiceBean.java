package se.lu.bos.misgen.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.deletebyquery.DeleteByQueryResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lu.bos.misgen.model.PlaneType;
import se.lu.bos.misgen.model.StaticObjectType;
import se.lu.bos.misgen.model.VehicleType;
import se.lu.bos.misgen.nosql.ElasticSearchServer;
import se.lu.bos.misgen.webmodel.ActionType;
import se.lu.bos.misgen.webmodel.ClientMission;
import se.lu.bos.misgen.webmodel.UnitGroup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 */
@RestController
@RequestMapping("/rest/missionbuilder")
public class MissionDataServiceBean {

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    ElasticSearchServer elasticSearchServer;

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
                .filter(pt -> pt.getCountry() == countryCode)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

}
