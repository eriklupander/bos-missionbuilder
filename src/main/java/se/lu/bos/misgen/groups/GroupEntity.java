package se.lu.bos.misgen.groups;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-01-05
 * Time: 20:03
 * To change this template use File | Settings | File Templates.
 */
public class GroupEntity {

    private float xPos; // From south to north??
    private float yPos = 0.0f; // altitude, probably only useful for airfields?
    private float zPos; // From west to east

    private String data; // This should the full object from { to }

    private GroupEntityType groupEntityType;

    public GroupEntity(GroupEntityType groupEntityType, Float xPos, Float zPos, String data) {

        this.groupEntityType = groupEntityType;
        this.xPos = xPos;
        this.zPos = zPos;
        this.data = data;
    }

    public GroupEntity(GroupEntityType groupEntityType, Float xPos, Float yPos, Float zPos, String data) {

        this.groupEntityType = groupEntityType;
        this.xPos = xPos;
        this.yPos = yPos;
        this.zPos = zPos;
        this.data = data;
    }

    public float getxPos() {
        return xPos;
    }

    public void setxPos(float xPos) {
        this.xPos = xPos;
    }

    public float getyPos() {
        return yPos;
    }

    public void setyPos(float yPos) {
        this.yPos = yPos;
    }

    public float getzPos() {
        return zPos;
    }

    public void setzPos(float zPos) {
        this.zPos = zPos;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public GroupEntityType getGroupEntityType() {
        return groupEntityType;
    }

    public void setGroupEntityType(GroupEntityType groupEntityType) {
        this.groupEntityType = groupEntityType;
    }
}
