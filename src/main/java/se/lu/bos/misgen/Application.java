package se.lu.bos.misgen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.task.TaskExecutor;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import se.lu.bos.misgen.nosql.ElasticSearchServer;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

//@EnableCaching

@EnableScheduling
@EnableAutoConfiguration
@EnableTransactionManagement
@ComponentScan
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[] { "se.lu.bos.misgen.model" });

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

//    @Value("${threadPool.db.init_size}")
//    private int THREAD_POOL_DB_INIT_SIZE;
//    @Value("${threadPool.db.max_size}")
//    private int THREAD_POOL_DB_MAX_SIZE;
//    @Value("${threadPool.db.queue_size}")
//    private int THREAD_POOL_DB_QUEUE_SIZE;
//
//
//    @Bean(name="dbThreadPoolExecutor")
//    public TaskExecutor getTaskExecutor() {
//        ThreadPoolTaskExecutor tpte = new ThreadPoolTaskExecutor();
//        tpte.setCorePoolSize(THREAD_POOL_DB_INIT_SIZE);
//        tpte.setMaxPoolSize(THREAD_POOL_DB_MAX_SIZE);
//        tpte.setQueueCapacity(THREAD_POOL_DB_QUEUE_SIZE);
//        tpte.initialize();
//        return tpte;
//    }

//    @Bean
//    public CacheManager cacheManager() {
//        // configure and return an implementation of Spring's CacheManager SPI
//        SimpleCacheManager cacheManager = new SimpleCacheManager();
//        cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("reportsCache")));
//        return cacheManager;
//    }
//
//    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:file:./db/misgen;AUTO_SERVER=TRUE;MVCC=TRUE");
        dataSource.setUsername( "misgen" );
        dataSource.setPassword( "misgen" );

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        return properties;
    }

//    @Configuration
//    protected static class MvcConfig extends WebMvcConfigurerAdapter {
//
//        @Override
//        public void addViewControllers(ViewControllerRegistry registry) {
//            registry.addViewController("/home").setViewName("home");
//            registry.addViewController("/").setViewName("home");
//            registry.addViewController("/hello").setViewName("hello");
//            registry.addViewController("/login").setViewName("login");
//        }
//    }

   // @Configuration
   // @EnableWebMvcSecurity
   // @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
//    protected static class ApplicationSecurity extends WebSecurityConfigurerAdapter {
//
////        @Autowired
////        private org.springframework.boot.autoconfigure.security.SecurityProperties security;
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
////            http.csrf().disable().httpBasic()
////                    .and()
////                    .authorizeRequests()
////                    .antMatchers("/rest/admin/**")
////                    .hasRole("ADMIN"); // .anyRequest().authenticated()
//
//
//            http.csrf().disable().authorizeRequests()
//                    .antMatchers("/", "/home").permitAll()
//                    .anyRequest().authenticated()
//                    .and()
//                    .formLogin()
//                    .loginPage("/login")
//                    .permitAll()
//                    .and()
//                    .logout()
//                    .permitAll();
//        }
//        @Override
//        public void configure(AuthenticationManagerBuilder auth) throws Exception {
//            auth.inMemoryAuthentication().withUser("dev").password("dev")
//                    .roles("ADMIN", "USER");
//        }
//    }
//
    @Bean(initMethod = "start", name = "esServer", destroyMethod = "stop")
    public ElasticSearchServer esServer() {
        Map<String,String> configuration = new HashMap<>();
        configuration.put("location","classpath:elasticsearch-server.properties");
        configuration.put("localOverride", "true");
      //  configuration.put("path.conf", "${webapp.root}/WEB-INF/config");
        return new ElasticSearchServer(configuration);
    }

}
