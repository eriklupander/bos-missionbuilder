package se.lu.bos.misgen.webmodel;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-01-21
 * Time: 22:41
 * To change this template use File | Settings | File Templates.
 */
public class MapObject {

    private MapObjectType objectType;

    private Float x;
    private Float y;
    private Float z;
    private Float yOri;

    private Long clientId;

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public Float getZ() {
        return z;
    }

    public void setZ(Float z) {
        this.z = z;
    }

    public Float getyOri() {
        return yOri;
    }

    public void setyOri(Float yOri) {
        this.yOri = yOri;
    }

    public MapObjectType getObjectType() {
        return objectType;
    }

    public void setObjectType(MapObjectType objectType) {
        this.objectType = objectType;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
