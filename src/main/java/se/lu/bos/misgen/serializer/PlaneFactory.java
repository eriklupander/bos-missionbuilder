package se.lu.bos.misgen.serializer;

import se.lu.bos.misgen.helper.GlobalId;
import se.lu.bos.misgen.model.MCUTREntity;
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
    public static Plane buildPlane(boolean playerGroup, PlaneType planeType, int numInGroup, int aiLevel, float x, float y, float z, float yOri, Integer payloadId) {
        Plane p = new Plane(x, y, z, yOri);
        p.setName("Plane " + numInGroup);
        p.getMCU_TR_Entity().setName(p.getName());
        p.setScript(planeType.getScript());
        p.setModel(planeType.getModel());
        p.setAILevel(aiLevel);
        p.setNumberInFormation(numInGroup);
        p.setCountry(planeType.getCountry());
        p.setPayloadId(payloadId);
        if(playerGroup && numInGroup == 0) {
            p.setAILevel(0);
        }
        return p;
    }
}
