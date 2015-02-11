package se.lu.bos.misgen.webmodel;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-01-28
 * Time: 22:53
 * To change this template use File | Settings | File Templates.
 */
public enum FormationType {
    COLUMN(7), LINE(6), ON_ROAD_COLUMN(4), OFF_ROAD_COLUMN(5);

    private int formationCode = 0;

    private FormationType(int formationCode) {
        this.formationCode = formationCode;
    }

    public int getFormationCode() {
        return formationCode;
    }
}
