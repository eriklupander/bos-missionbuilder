package se.lu.bos.misgen.model;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-27
 * Time: 00:09
 * To change this template use File | Settings | File Templates.
 */
public class WindLayer  extends BaseEntity {

    private Integer altitude;
    private Integer headingDegrees;
    private Float speed;

    public WindLayer() {
    }

    public WindLayer(Integer altitude, Integer headingDegrees, Float speed) {
        this.altitude = altitude;
        this.headingDegrees = headingDegrees;
        this.speed = speed;
    }

    public Integer getAltitude() {
        return altitude;
    }

    public void setAltitude(Integer altitude) {
        this.altitude = altitude;
    }

    public Integer getHeadingDegrees() {
        return headingDegrees;
    }

    public void setHeadingDegrees(Integer headingDegrees) {
        this.headingDegrees = headingDegrees;
    }

    public Float getSpeed() {
        return speed;
    }

    public void setSpeed(Float speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "" + altitude + " :" + headingDegrees + " :" + speed + ";";
    }
}
