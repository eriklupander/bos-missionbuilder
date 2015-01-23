package se.lu.bos.misgen.nosql;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-09-11
 * Time: 23:50
 * To change this template use File | Settings | File Templates.
 */
@Configuration
public class ElasticSearchServerConfiguration {

    @Bean
    public PropertiesFactoryBean elasticSearchConfiguration() {
        return new PropertiesFactoryBean();
    }
     /*
    <bean id="elasticSearchConfiguration"
    class="org.springframework.beans.factory.config.PropertiesFactoryBean">
    <property name="location" value="classpath:elasticsearch-server.properties"/>
    <property name="localOverride" value="true"/>
    <property name="properties">
    <props>
    <prop key="path.conf">${webapp.root}/WEB-INF/config</prop>
    </props>
    </property>
    </bean>
     */
    @Bean
    public ElasticSearchServer esServer() {
        Map<String,String> configuration = new HashMap<>();
        configuration.put("location","classpath:elasticsearch-server.properties");
        configuration.put("localOverride", "true");
        configuration.put("path.conf", "${webapp.root}/WEB-INF/config");
        return new ElasticSearchServer(configuration);
    }
    /*
    <bean id="esServer" class="my.elasticsearch.ElasticSearchServer"
    init-method="start" destroy-method="stop">
    <constructor-arg ref="elasticSearchConfiguration"/>
    </bean>
    */
}
