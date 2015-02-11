package se.lu.bos.misgen.webmodel;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-02-01
 * Time: 11:46
 * To change this template use File | Settings | File Templates.
 */
public class ClientAirfield extends MapObject {

    private Integer lcId;
    private String name;

    public Integer getLcId() {
        return lcId;
    }

    public void setLcId(Integer lcId) {
        this.lcId = lcId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
