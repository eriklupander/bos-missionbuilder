package se.lu.bos.misgen.model;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-03-10
 * Time: 21:17
 * To change this template use File | Settings | File Templates.
 */
public enum EventType {
    ON_DAMAGED(12), ON_KILLED(13), ON_PLANE_TOOK_OFF(6), ON_PLANE_DESTROYED(4);

    private final int eventId;

    private EventType(int eventId) {
        this.eventId = eventId;
    }

    public int getEventId() {
        return eventId;
    }
}
