package se.lu.bos.misgen.serializer;

import se.lu.bos.misgen.groups.StaticGroupsFactory;
import se.lu.bos.misgen.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-27
 * Time: 00:24
 * To change this template use File | Settings | File Templates.
 */
public class MissionWriter {

    private int index = 0;

    public String generateMission(GeneratedMission gm) throws IOException {
        List<GameEntity> entities = new ArrayList<>();
        entities.addAll(gm.getAreaAttackCommands());
        entities.addAll(gm.getAttackTargetCommands());
        entities.addAll(gm.getCoverCommands());
        entities.addAll(gm.getTakeOffCommands());
        entities.addAll(gm.getLandCommands());
        entities.addAll(gm.getTimers());
        entities.addAll(gm.getTranslatorIcons());
        entities.addAll(gm.getTranslatorMissionBegins());
        entities.addAll(gm.getTranslatorSubtitles());
        entities.addAll(gm.getWayPoints());
        entities.addAll(gm.getStaticObjects());
        entities.addAll((Collection<? extends GameEntity>) gm.getObjectGroups()
                .stream().map(og -> og.getObjects())
                .flatMap(s -> s.stream())
                .collect(Collectors.toList()));


        List<GameEntity> sortedEntities = entities.stream().sorted((GameEntity ge1, GameEntity ge2) -> ge1.getId().compareTo(ge2.getId())).collect(Collectors.toList());

        StringBuilder buf = new StringBuilder();
        buf.append("# Mission File Version = 1.0;\r\n");
        buf.append(gm.getMissionOptions().toString());
        buf.append("\r\n\r\n\r\n");
        sortedEntities.stream().forEach(e -> buf.append(e.toString()));
        buf.append("\r\n\r\n\r\n");
        gm.getAirfieldEntities().stream().forEach(e -> buf.append(e.toString()).append("\r\n"));
        buf.append("\r\n");
        gm.getAirfields().stream().forEach(e -> buf.append(e.getData()).append("\r\n"));
        gm.getTowns().stream().forEach(e -> buf.append(e.getData()).append("\r\n"));
      //  gm.getBridges().stream().forEach(e -> buf.append(e.getData()).append("\r\n"));
      //  gm.getRailwayStations().stream().forEach(e -> buf.append(e.getData()).append("\r\n"));
        buf.append("# end of file");
        return buf.toString();
    }

    public String generateMission(MissionOptions missionOptions, List<Plane> planes, List<Vehicle> vehicles) throws IOException {
        StringBuffer buf = new StringBuffer();
        buf.append("# Mission File Version = 1.0;\r\n");
        buf.append(missionOptions.toString());
        buf.append("\r\n\r\n\r\n");
        buf.append(StaticGroupsFactory.getAirfields());
        buf.append("\r\n\r\n\r\n");
        for(Plane plane : planes) {
            buf.append(plane.toString());
            buf.append("\r\n\r\n\r\n");
        }

        buf.append("\r\n\r\n\r\n");

        for(Vehicle v : vehicles) {
            buf.append(v.toString());
            buf.append("\r\n\r\n\r\n");
        }
        buf.append("# end of file");
        return buf.toString();
    }

}
