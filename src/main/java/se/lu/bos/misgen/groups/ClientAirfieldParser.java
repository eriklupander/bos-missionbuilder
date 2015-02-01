package se.lu.bos.misgen.groups;

import se.lu.bos.misgen.webmodel.ClientAirfield;
import se.lu.bos.misgen.webmodel.MapObjectType;

import java.io.IOException;
import java.util.List;

/**
 * Combines data from MCU_Icons and a translation fiel
 *
 */
public class ClientAirfieldParser {

    public static List<ClientAirfield> build() throws IOException {
        List<ClientAirfield> airfields = AirfieldNameReader.buildAirfields();
        List<GroupEntity> groupEntities = new StaticGroupReader().readGroupEntities("airfieldicons.group", GroupEntityType.AIRFIELD_ICONS);

        for(GroupEntity ge : groupEntities)  {
            for(ClientAirfield clientAirfield : airfields) {
                if(ge.getData().contains("LCName = " + clientAirfield.getLcId() + ";")) {
                    clientAirfield.setX(ge.getxPos());
                    clientAirfield.setY(ge.getyPos());
                    clientAirfield.setZ(ge.getzPos());
                    clientAirfield.setObjectType(MapObjectType.AIRFIELD);
                    break;
                }
            }
        }

        return airfields;
    }

}
