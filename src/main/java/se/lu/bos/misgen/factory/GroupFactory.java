package se.lu.bos.misgen.factory;

import se.lu.bos.misgen.helper.ObjectGroup;
import se.lu.bos.misgen.model.Plane;
import se.lu.bos.misgen.model.PlaneType;
import se.lu.bos.misgen.model.Vehicle;
import se.lu.bos.misgen.model.VehicleType;
import se.lu.bos.misgen.webmodel.FormationType;
import se.lu.bos.misgen.webmodel.UnitGroup;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-01-02
 * Time: 20:46
 * To change this template use File | Settings | File Templates.
 */
public class GroupFactory {

//    public static ObjectGroup buildPlaneGroup(boolean playerGroup, int numberOfPlanes, PlaneType planeType, boolean startInAir, float x, float y, float z, float yOri, Integer payloadId, String baseName, FormationType formationType) {
//
//        ObjectGroup objectGroup = new ObjectGroup();
//        for(int a = 0; a < numberOfPlanes; a++) {
//            objectGroup.getObjects().add(PlaneFactory.buildPlane(playerGroup, planeType, a, 2, x, y, z, yOri, payloadId, baseName, player));
//        }
//        objectGroup.getObjects().stream().forEach( o -> {
//            if(startInAir) {
//                ((Plane)o).setStartInAir(0);
//            } else {
//                ((Plane)o).setStartInAir(1);
//            }
//            if(((Plane) o).getNumberInFormation() > 0) {
//                // Make sure wingmen entity target the flight leader entity
//                o.getMCU_TR_Entity().getTargets().add(objectGroup.getObjects().get(0).getMCU_TR_Entity().getId().intValue());
//            }
//        });
//        objectGroup.applyPosition(x, y, z, yOri, formationType);
//
//        return objectGroup;
//    }

    public static ObjectGroup buildVehicleGroup(int numberOfVehicles, VehicleType vehicleType, float x, float y, float z, float yOri, FormationType formationType) {

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
        objectGroup.applyPosition(x, y, z, yOri, formationType);

        return objectGroup;
    }

    public static ObjectGroup buildPlaneGroup(UnitGroup ug, FormationType formationType) {
        ObjectGroup objectGroup = new ObjectGroup();
        for(int a = 0; a < ug.getSize(); a++) {
            Plane plane = PlaneFactory.buildPlane(ug.getAiLevel() == 0, PlaneType.valueOf(ug.getType()), a,
                    ug.getAiLevel(), ug.getX(), ug.getY(), ug.getZ(), ug.getyOri(), ug.getLoadout(), ug.getName(), ug.getPlayerIndex());

            objectGroup.getObjects().add(plane);
        }
        objectGroup.getObjects().stream().forEach( o -> {
            if(ug.getY() != 0) {
                ((Plane)o).setStartInAir(0);
            } else {
                ((Plane)o).setStartInAir(1);
            }
            if(((Plane) o).getNumberInFormation() > 0) {
                // Make sure wingmen entity target the flight leader entity
                o.getMCU_TR_Entity().getTargets().add(objectGroup.getObjects().get(0).getMCU_TR_Entity().getId().intValue());
            }

            applySkin((Plane) o, ug);
        });
        objectGroup.applyPosition(ug.getX(), ug.getY(), ug.getZ(), ug.getyOri(), formationType);

        return objectGroup;
    }

    private static void applySkin(Plane plane, UnitGroup ug) {
        if(ug.getSkins() == null || ug.getSkins().size() == 0) {
            plane.setSkin("");
        } else {
            if(ug.getSkins().size() > plane.getNumberInFormation()) {
                String skin = ug.getSkins().get(plane.getNumberInFormation());
                if("Default".equalsIgnoreCase(skin)) {
                    plane.setSkin("");
                } else {
                    plane.setSkin(PlaneType.valueOf(ug.getType()).getSkinFolder() + "\\" + skin);
                }
            }
        }
    }
}
