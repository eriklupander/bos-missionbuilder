package se.lu.bos.misgen.sec;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.access.ContextSingletonBeanFactoryLocator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import se.lu.bos.misgen.Application;
import se.lu.bos.misgen.nosql.ElasticSearchServer;

/**
 * Realm for storing auth data in an ElasticSearch DB
 */

@Component
public class ElasticSearchAuthenticatingRealm extends AuthenticatingRealm  {

    private static final Logger log = LoggerFactory.getLogger(ElasticSearchAuthenticatingRealm.class);



    ObjectMapper objectMapper = new ObjectMapper();

    public ElasticSearchAuthenticatingRealm() {
        log.info("Creating instance of ElasticSearchAuthenticatingRealm...: " + this.hashCode());
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        ElasticSearchServer elasticSearchServer = Application.getESServer();
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        if (token.getUsername() == null)
            throw new AccountException("Username missing, can't log in");

        try {
            String userString = elasticSearchServer.getClient().prepareGet("users", "user", token.getPrincipal().toString()).execute().actionGet().getSourceAsString();
            log.info(userString);
            User user = objectMapper.readValue(userString, User.class);
            String credz = new String(token.getPassword());
            //String hashed = new DefaultPasswordService().encryptPassword(token.getCredentials().toString());
            //if(hashed.equals(user.getPassword())) {
                SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(), credz, "elasticSearchRealm");
                return authenticationInfo;
           // } else {
            //    log.warn("Hashes did not match: " + hashed + " != " + user.getPassword());
            //}
            //return null;

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
