package se.lu.bos.misgen.groups;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-01-05
 * Time: 20:01
 * To change this template use File | Settings | File Templates.
 */
public class StaticGroupReader {

    public List<GroupEntity> readGroupEntities(String fileName, GroupEntityType groupEntityType) throws IOException {
        switch(groupEntityType) {
            case AIRFIELD:
                return parseAirfields(readGroupFromFile(fileName));
            case BRIDGE:
                return parseBridges(readGroupFromFile(fileName));
            case TOWN:
                return parseTowns(readGroupFromFile(fileName));
            case STALINGRAD_CITY:
                return parseStalinGradCity(readGroupFromFile(fileName));
            case TRAIN_STATION:
                return parseTrainStations(readGroupFromFile(fileName));
        }
        return null;
    }

    private List<GroupEntity> parseStalinGradCity(String s) {
        List<GroupEntity> stalingradCity = new ArrayList<>();
        stalingradCity.add(new GroupEntity(GroupEntityType.STALINGRAD_CITY, parseXPos(s), parseZPos(s), s));
        return stalingradCity;
    }

    private List<GroupEntity> parseAirfields(String src) {
        List<GroupEntity> airfields = new ArrayList<>();

        Scanner scanner = new Scanner(src);
        StringBuilder buf = new StringBuilder();
        boolean isBuildingGroup = false;
        int depth = 0;
        while(scanner.hasNext()) {
            String line = scanner.nextLine();
            if(!isBuildingGroup) {
                if(line.trim().contains("{")) {
                    // START SCANNING
                    buf = new StringBuilder();
                    isBuildingGroup = true;
                    depth = 0;
                }
            } else {
                // Currently building group.
                if(line.trim().contains("{")) {
                    depth++;
                    buf.append(line).append("\r\n");
                } else if(line.trim().contains("}")) {
                    if(depth == 0) {
                        // LAST LINE BUILD OBJECT NOW!!
                        GroupEntity groupEntity = new GroupEntity(GroupEntityType.TOWN, parseXPos(buf.toString()), parseZPos(buf.toString()), "Group\r\n{\r\n" + buf.toString() + "}\r\n");
                        airfields.add(groupEntity);
                        buf.setLength(0);
                        isBuildingGroup = false;
                    } else {
                        depth--;
                        buf.append(line).append("\r\n");
                    }
                } else {
                    // ADD LINE TO BUFFER
                    buf.append(line).append("\r\n");
                }
            }

        }
        return airfields;
    }

    private List<GroupEntity> parseTrainStations(String src) {

            String[] blocks = src.split("}\r\n");
            List<String> strings = Arrays.asList(blocks);
            List<GroupEntity> remapped = strings.parallelStream()
                    .map(s -> s += "}\r\n")
                    .filter(s -> s.trim().contains("  Name = \"Block\";"))
                    .map(s -> new GroupEntity(GroupEntityType.TRAIN_STATION, parseXPos(s), parseZPos(s), s))
                    .collect(Collectors.toList());

            return remapped;

    }

    // TODO FIX BUG: This writes Group instead of Block at the root element when there is no sub-entities!!!
    private List<GroupEntity> parseTowns(String s) {
        List<GroupEntity> cities = new ArrayList<>();

        Scanner scanner = new Scanner(s);
        StringBuilder buf = new StringBuilder();
        boolean isBuildingGroup = false;
        int depth = 0;
        String previousLine = null;
        String objectType = null;
        while(scanner.hasNext()) {
            String line = scanner.nextLine();
            if(!isBuildingGroup) {
                if(line.trim().contains("{")) {
                    // START SCANNING
                    buf = new StringBuilder();
                    isBuildingGroup = true;
                    depth = 0;
                    objectType = previousLine;
                }
            } else {
                // Currently building group.
                if(line.trim().contains("{")) {
                    depth++;
                    buf.append(line).append("\r\n");
                } else if(line.trim().contains("}")) {
                    if(depth == 0) {
                        // LAST LINE BUILD OBJECT NOW!!
                        GroupEntity groupEntity = new GroupEntity(GroupEntityType.TOWN, parseXPos(buf.toString()), parseZPos(buf.toString()), objectType + "\r\n{\r\n" + buf.toString() + "}\r\n");
                        cities.add(groupEntity);
                        buf.setLength(0);
                        isBuildingGroup = false;
                    } else {
                        depth--;
                        buf.append(line).append("\r\n");
                    }
                } else {
                    // ADD LINE TO BUFFER
                    buf.append(line).append("\r\n");
                }
            }
            previousLine = line;
        }
        return cities;
    }


    private List<GroupEntity> parseBridges(String src) {
        String[] blocks = src.split("}\r\n");
        List<String> strings = Arrays.asList(blocks);
        List<GroupEntity> remapped = strings.parallelStream()
                .map(s -> s += "}\r\n")
                .filter(s -> s.trim().contains("  Name = \"Bridge\";"))
                .map(s -> new GroupEntity(GroupEntityType.BRIDGE, parseXPos(s), parseZPos(s), s))
                .collect(Collectors.toList());

        return remapped;
    }

    Pattern xp = Pattern.compile("(XPos = )(\\d*\\.?\\d*)");
    Pattern zp = Pattern.compile("(ZPos = )(\\d*\\.?\\d*)");

    private Float parseXPos(String src) {
        Matcher m = xp.matcher(src);
        if(m.find()) {
            return Float.parseFloat(m.group(2));
        }
        throw new IllegalArgumentException("Could not parse xpos from row '" + src + "'");
    }


    private Float parseZPos(String src) {
        Matcher m = zp.matcher(src);
        if(m.find()) {
            return Float.parseFloat(m.group(2));
        }
        throw new IllegalArgumentException("Could not parse zpos from row '" + src + "'");
    }

    private String readGroupFromFile(String file) throws IOException {
        InputStream resourceAsStream = StaticGroupsFactory.class.getClassLoader().getResourceAsStream(file);
        return IOUtils.toString(resourceAsStream);
    }

}
