package se.lu.bos.misgen.webmodel;

import java.util.HashSet;
import java.util.Set;

/**
 * Defines a zone at a world coordinate having a radius.
 *
 * Holds an ID list of subjects that will trigger all actions on ENTER.
 */
public class TriggerZone extends MapObject {

    private String name;
    private Integer radius;

    private Set<Integer> subjects  = new HashSet<Integer>();
    private Set<Action> actions = new HashSet<Action>();

    public TriggerZone() {
        setObjectType(MapObjectType.TRIGGER);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRadius() {
        return radius;
    }

    public Set<Integer> getSubjects() {
        return subjects;
    }

    public Set<Action> getActions() {
        return actions;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }

    public void setSubjects(Set<Integer> subjects) {
        this.subjects = subjects;
    }

    public void setActions(Set<Action> actions) {
        this.actions = actions;
    }
}
