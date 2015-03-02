package se.lu.bos.misgen.webmodel;

import se.lu.bos.misgen.model.EffectType;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-03-02
 * Time: 22:48
 * To change this template use File | Settings | File Templates.
 */
public class Effect extends MapObject {

    private EffectType effectType;

    public EffectType getEffectType() {
        return effectType;
    }

    public void setEffectType(EffectType effectType) {
        this.effectType = effectType;
    }
}
