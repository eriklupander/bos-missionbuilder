package se.lu.bos.misgen.startup;

/**
 * Created with IntelliJ IDEA.
 * User: eriklupander
 * Date: 15-02-11
 * Time: 16:50
 * To change this template use File | Settings | File Templates.
 */
//@Component
public class Bootstrap {

//    private static final Logger log = LoggerFactory.getLogger(Bootstrap.class);
//
//    @Autowired
//    ElasticSearchServer elasticSearchServer;
//
//    @Autowired
//    UserDao userDao;
//
//    private ObjectMapper objectMapper = new ObjectMapper();
//
//    @PostConstruct
//    public void init() throws IOException {
//        try {
//
//            List<User> esData = getUsers();
//
//            if(!esData.stream().anyMatch(u -> u.getUsername().equals("test"))) {
//                userDao.createUser("test", "test"); // TODO read these from properties file instead for production usage.
//            }
//        } catch (Exception e) {
//            log.error("Error occurred checking or creating default user: " + e.getMessage());
//        }
//    }
//
//    private List<User> getUsers() {
//        List<User> esData = new ArrayList<>();
//
//        try {
//            SearchResponse response = elasticSearchServer.getClient().prepareSearch("users")
//                    .setTypes("user")
//                    .setQuery(QueryBuilders.matchAllQuery())
//                    .execute()
//                    .actionGet();
//
//            List<User> collect = Arrays.asList(response.getHits().getHits()).stream()
//                    .map(hit -> {
//                        try {
//                            return objectMapper.readValue(hit.getSourceAsString(), User.class);
//                        } catch (IOException e) {
//                            throw new RuntimeException(e.getMessage());
//                        }
//                    }
//
//                    ).collect(Collectors.toList());
//            esData.addAll(collect);
//        } catch (Exception e) {
//            log.warn("Could not query for users in the DB, returning empty list...");
//        }
//        return esData;
//    }


}
