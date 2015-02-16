package se.lu.bos.misgen.webmodel;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-01-28
 * Time: 22:53
 * To change this template use File | Settings | File Templates.
 */
public enum FormationType {
    LINE(6), COLUMN(7), ON_ROAD_COLUMN(4);

    private int formationCode = 0;

    private FormationType(int formationCode) {
        this.formationCode = formationCode;
    }

    public int getFormationCode() {
        return formationCode;
    }

    public static FormationType from(Integer formation) {
        switch(formation) {
            case 7:
                return COLUMN;
            case 6:
                return LINE;
            case 4:
                return ON_ROAD_COLUMN;
            default:
                return LINE;
        }
    }
}
