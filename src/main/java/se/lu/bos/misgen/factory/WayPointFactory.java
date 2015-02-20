package se.lu.bos.misgen.factory;

import se.lu.bos.misgen.model.GameEntity;
import se.lu.bos.misgen.model.WayPoint;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-01-03
 * Time: 21:26
 * To change this template use File | Settings | File Templates.
 */
public class WayPointFactory {

    public static WayPoint buildWayPoint(float x, float y, float z, GameEntity target) {
        return buildWayPoint(x, y, z, target, 400, 250, 1);
    }

    public static WayPoint buildWayPoint(float x, float y, float z, GameEntity target, Integer area, Integer speed, Integer priority) {
        WayPoint wp = new WayPoint(x, y, z, area, speed, priority);
        wp.getObjects().add(target.getMCU_TR_Entity().getId().intValue());

        // Generate an immediate timer as WP target. Wonder which ID to use though...

        //wp.getTargets().add(wp.getTimer().getId().intValue());
        return wp;
    }

}
