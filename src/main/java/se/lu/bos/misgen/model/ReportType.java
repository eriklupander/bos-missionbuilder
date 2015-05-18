package se.lu.bos.misgen.model;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-03-10
 * Time: 21:30
 * To change this template use File | Settings | File Templates.
 */
public enum ReportType {
    ON_TOOK_OFF(3), ON_LANDED(4);

    private final int eventId;

    private ReportType(int eventId) {
        this.eventId = eventId;
    }

    public int getEventId() {
        return eventId;
    }
}
