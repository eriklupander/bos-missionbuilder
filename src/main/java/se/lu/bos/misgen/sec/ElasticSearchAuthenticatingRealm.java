package se.lu.bos.misgen.sec;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.lu.bos.misgen.nosql.ElasticSearchServer;

/**
 * Realm for storing auth data in an ElasticSearch DB
 */
@Component
public class ElasticSearchAuthenticatingRealm extends AuthenticatingRealm {

    private static final Logger log = LoggerFactory.getLogger(ElasticSearchAuthenticatingRealm.class);

    @Autowired
    ElasticSearchServer elasticSearchServer;

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        if (token.getUsername() == null)
            throw new AccountException("Username missing, can't log in");

        try {
            String userString = elasticSearchServer.getClient().prepareGet("users", "user", token.getPrincipal().toString()).execute().actionGet().getSourceAsString();
            log.info(userString);
            User user = objectMapper.readValue(userString, User.class);

            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), "elasticSearchRealm");
            return authenticationInfo;
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
