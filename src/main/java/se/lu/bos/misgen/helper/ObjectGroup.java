package se.lu.bos.misgen.helper;

import se.lu.bos.misgen.model.GameEntity;
import se.lu.bos.misgen.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-01-02
 * Time: 20:56
 * To change this template use File | Settings | File Templates.
 */
public class ObjectGroup {

    // Group members should use these coords as base location.
    private Float XPos = 95946.699f;  // North/South
    private Float YPos = 85.608f;     // Height
    private Float ZPos = 50313.633f;  // East/West
    private Float XOri = 0.00f;
    private Float YOri = 89.08f;      // Heading of nose
    private Float ZOri = 14.80f;

    private List<GameEntity> objects = new ArrayList<>();

    public Float getXPos() {
        return XPos;
    }

    public void setXPos(Float XPos) {
        this.XPos = XPos;
    }

    public Float getYPos() {
        return YPos;
    }

    public void setYPos(Float YPos) {
        this.YPos = YPos;
    }

    public Float getZPos() {
        return ZPos;
    }

    public void setZPos(Float ZPos) {
        this.ZPos = ZPos;
    }

    public Float getXOri() {
        return XOri;
    }

    public void setXOri(Float XOri) {
        this.XOri = XOri;
    }

    public Float getYOri() {
        return YOri;
    }

    public void setYOri(Float YOri) {
        this.YOri = YOri;
    }

    public Float getZOri() {
        return ZOri;
    }

    public void setZOri(Float ZOri) {
        this.ZOri = ZOri;
    }

    public List<GameEntity> getObjects() {
        return objects;
    }

    public void setObjects(List<GameEntity> objects) {
        this.objects = objects;
    }

    public Integer getLeaderId() {
        if(this.getObjects().size() > 0) {
            return this.getObjects().get(0).getMCU_TR_Entity().getId().intValue();
        }
        return -1;
    }

    public GameEntity getLeader() {
        if(this.getObjects().size() > 0) {
            return this.getObjects().get(0);
        }
        return null;
    }

    public void applyPosition(float x, float y, float z) {
        for(int a = 0; a < objects.size(); a++) {
            GameEntity o = objects.get(a);
            o.setXPos(x+ Util.getOffset(a));
            o.setYPos(y);
            o.setZPos(z+Util.getOffset(a));
            o.setXOri(getXOri());
            o.setYOri(getYOri());
            o.setZOri(getZOri());

            o.getMCU_TR_Entity().setXPos(o.getXPos());
            o.getMCU_TR_Entity().setYPos(o.getYPos());
            o.getMCU_TR_Entity().setZPos(o.getZPos());
        }
    }

    public void applyYOrientation(float y) {
        objects.stream().forEach(o -> o.setYOri(y));
    }


}
