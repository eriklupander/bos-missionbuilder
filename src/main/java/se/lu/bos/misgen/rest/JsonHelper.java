package se.lu.bos.misgen.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import se.lu.bos.misgen.webmodel.ClientMission;

import java.io.IOException;
import java.util.function.Function;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2015-01-20
 * Time: 22:16
 * To change this template use File | Settings | File Templates.
 */
public class JsonHelper implements Function<String, ClientMission> {

    private ObjectMapper om = new ObjectMapper();

    @Override
    public ClientMission apply(String json) {
        try {
            return om.readValue(json, ClientMission.class);
        } catch (IOException e) {
            return null;
        }
    }
}
