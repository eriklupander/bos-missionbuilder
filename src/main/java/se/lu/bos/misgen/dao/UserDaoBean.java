package se.lu.bos.misgen.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.elasticsearch.action.index.IndexResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.lu.bos.misgen.sec.User;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: eriklupander
 * Date: 15-02-13
 * Time: 16:41
 * To change this template use File | Settings | File Templates.
 */
@Component
public class UserDaoBean implements UserDao {

    private static final Logger log = LoggerFactory.getLogger(UserDaoBean.class);

    @Autowired
    se.lu.bos.misgen.nosql.ElasticSearchServer elasticSearchServer;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public User findUser(String username) {

        try {
            String userString = elasticSearchServer.getClient().prepareGet("users", "user", username).execute().actionGet().getSourceAsString();
            if(userString == null) return null;
            return objectMapper.readValue(userString, User.class);
        } catch (Throwable e) {
            return null;
        }
    }

    @Override
    public User createUser(String username, String password) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(new DefaultPasswordService().encryptPassword(password));

        String json = null;
        try {
            json = objectMapper.writeValueAsString(newUser);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
        IndexResponse indexResponse = elasticSearchServer.getClient().prepareIndex("users", "user")
                .setSource(json)
                .setId(newUser.getUsername())
                .execute()
                .actionGet();
        log.info("Successfully created default BoS missionparser user.");

        return newUser;
    }

}
