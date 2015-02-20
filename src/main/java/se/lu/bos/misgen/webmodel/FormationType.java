package se.lu.bos.misgen.webmodel;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-01-28
 * Time: 22:53
 * To change this template use File | Settings | File Templates.
 */
public enum FormationType {
    NONE(0, 0), VEE(1, 0), LEFT_EDGE(2, 0), RIGHT_EDGE(3, 0), LINE(6, 1), COLUMN(7, 1), ON_ROAD_COLUMN(4, 1);

    private int formationCode = 0;
    private int category = 0; // 0 == plane, 1 == ground

    private FormationType(int formationCode, int category) {
        this.formationCode = formationCode;
        this.category = category;
    }

    public int getFormationCode() {
        return formationCode;
    }

   public int getCategory() {
       return category;
   }

    public static FormationType from(Integer formation) {
        switch(formation) {
            case 1:
                return VEE;
            case 2:
                return LEFT_EDGE;
            case 3:
                return RIGHT_EDGE;
            case 7:
                return COLUMN;
            case 6:
                return LINE;
            case 4:
                return ON_ROAD_COLUMN;
            default:
                return NONE;
        }
    }
}
