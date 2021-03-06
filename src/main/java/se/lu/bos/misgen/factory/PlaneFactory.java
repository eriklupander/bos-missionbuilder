package se.lu.bos.misgen.factory;

import se.lu.bos.misgen.model.Plane;
import se.lu.bos.misgen.model.PlaneType;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-01-02
 * Time: 20:46
 * To change this template use File | Settings | File Templates.
 */
public class PlaneFactory {
    public static Plane buildPlane(boolean playerGroup, PlaneType planeType, int numInGroup, int aiLevel, float x, float y, float z, float yOri, Integer payloadId, String baseName, int playerIndex) {
        Plane p = new Plane(x, y, z, yOri);
        p.setName(baseName + " " + (numInGroup+1) + " (" + planeType.name() + ")");
        p.getMCU_TR_Entity().setName(p.getName());
        p.setScript(planeType.getScript());
        p.setModel(planeType.getModel());
        p.setAILevel(aiLevel);
        p.setNumberInFormation(numInGroup);
        p.setCountry(planeType.getCountry());
        p.setPayloadId(payloadId);

        // Handle player index in flight if applicable.
        if(playerGroup) {
            if(numInGroup == (playerIndex-1)) {
                p.setAILevel(0);
            } else {
                p.setAILevel(3);
            }
        }
        p.setStartInAir(y == 0 ? 1 : 0);
        return p;
    }
}
