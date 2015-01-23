package se.lu.bos.misgen.serializer;

import se.lu.bos.misgen.model.Vehicle;
import se.lu.bos.misgen.model.VehicleType;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-01-02
 * Time: 20:46
 * To change this template use File | Settings | File Templates.
 */
public class VehicleFactory {

    public static Vehicle buildVehicle(VehicleType vehicleType, int numInGroup, int aiLevel, float x, float y, float z, float yOri) {
        Vehicle v = new Vehicle(x, y, z, yOri);
        v.setName("Vehicle " + numInGroup);
        v.getMCU_TR_Entity().setName(v.getName());
        v.setScript(vehicleType.getScript());
        v.setModel(vehicleType.getModel());
        v.setAILevel(aiLevel);
        v.setNumberInFormation(numInGroup);
        v.setCountry(vehicleType.getCountry());

        return v;
    }
}
