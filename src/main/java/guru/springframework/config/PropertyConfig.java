package guru.springframework.config;

import guru.springframework.examplebeans.FakeDataSource;
import guru.springframework.examplebeans.FakeJmsBRoker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

@Configuration
// moved to application.properties
//@PropertySource({"classpath:datasource.properties", "classpath:jms.properties"})
// alternative grouping annotation
//@PropertySources({
//    @PropertySource("classpath:datasource.properties"),
//    @PropertySource("classpath:jms.properties")
//})
public class PropertyConfig {

    @Autowired
    Environment env;

    @Value("${remotesource.user}")
    String user;

    @Value("${remotesource.password}")
    String password;

    @Value("${remotesource.dburl}")
    String url;

    @Value("${jms.user}")
    String jmsUser;

    @Value("${jms.password}")
    String jmsPassword;

    @Value("${jms.dburl}")
    String jmsUrl;

    @Bean
    public FakeDataSource fakeDataSource(){
        FakeDataSource fakeDataSource = new FakeDataSource();
        // example using environment variable
        // fakeDataSource.setUser(env.getProperty("USER"));
        fakeDataSource.setUser(user);
        fakeDataSource.setPassword(password);
        fakeDataSource.setUrl(url);
        return fakeDataSource;
    }

    @Bean
    public FakeJmsBRoker fakeJmsBRoker(){
        FakeJmsBRoker fakeJmsBRoker = new FakeJmsBRoker();
        fakeJmsBRoker.setUser(jmsUser);
        fakeJmsBRoker.setPassword(jmsPassword);
        fakeJmsBRoker.setUrl(jmsUrl);
        return fakeJmsBRoker;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties(){
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        return propertySourcesPlaceholderConfigurer;
    }
}
