package se.lu.bos.misgen.serializer;

import se.lu.bos.misgen.helper.ObjectGroup;
import se.lu.bos.misgen.model.Plane;
import se.lu.bos.misgen.model.PlaneType;
import se.lu.bos.misgen.model.Vehicle;
import se.lu.bos.misgen.model.VehicleType;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-01-02
 * Time: 20:46
 * To change this template use File | Settings | File Templates.
 */
public class GroupFactory {

    public static ObjectGroup buildPlaneGroup(boolean playerGroup, int numberOfPlanes, PlaneType planeType, boolean startInAir, float x, float y, float z, float yOri) {

        ObjectGroup objectGroup = new ObjectGroup();
        for(int a = 0; a < numberOfPlanes; a++) {
            objectGroup.getObjects().add(PlaneFactory.buildPlane(playerGroup, planeType, a, 2, x, y, z, yOri));
        }
        objectGroup.getObjects().stream().forEach( o -> {
            if(startInAir) {
                ((Plane)o).setStartInAir(0);
            } else {
                ((Plane)o).setStartInAir(1);
            }
            if(((Plane) o).getNumberInFormation() > 0) {
                // Make sure wingmen entity target the flight leader entity
                o.getMCU_TR_Entity().getTargets().add(objectGroup.getObjects().get(0).getMCU_TR_Entity().getId().intValue());
            }
        });
        objectGroup.applyPosition(x, y, z);

        return objectGroup;
    }

    public static ObjectGroup buildVehicleGroup(int numberOfVehicles, VehicleType vehicleType, float x, float y, float z, float yOri) {

        ObjectGroup objectGroup = new ObjectGroup();
        for(int a = 0; a < numberOfVehicles; a++) {
            objectGroup.getObjects().add(VehicleFactory.buildVehicle(vehicleType, a, 2, x, y, z, yOri));
        }
        objectGroup.getObjects().stream().forEach( o -> {

            if(((Vehicle) o).getNumberInFormation() > 0) {
                // Make sure additional entities target the  leader entity
                o.getMCU_TR_Entity().getTargets().add(objectGroup.getObjects().get(0).getMCU_TR_Entity().getId().intValue());
            }
        });
        objectGroup.applyPosition(x, y, z);

        return objectGroup;
    }

}
