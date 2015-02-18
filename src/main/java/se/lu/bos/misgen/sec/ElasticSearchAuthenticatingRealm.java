package se.lu.bos.misgen.sec;

/**
 * Realm for storing auth data in an ElasticSearch DB
 */

//@Component
public class ElasticSearchAuthenticatingRealm { // extends AuthenticatingRealm  {

//    private static final Logger log = LoggerFactory.getLogger(ElasticSearchAuthenticatingRealm.class);
//
//    private ObjectMapper objectMapper = new ObjectMapper();
//
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        ElasticSearchServer elasticSearchServer = Application.getESServer();
//        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
//        if (token.getUsername() == null)
//            throw new AccountException("Username missing, can't log in");
//
//        try {
//            String userString = elasticSearchServer.getClient().prepareGet("users", "user", token.getPrincipal().toString()).execute().actionGet().getSourceAsString();
//            log.info(userString);
//            User user = objectMapper.readValue(userString, User.class);
//            String credz = new String(token.getPassword());
//
//            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), "elasticSearchRealm");
//            return authenticationInfo;
//
//
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            e.printStackTrace();
//            return null;
//        }
//    }
}
