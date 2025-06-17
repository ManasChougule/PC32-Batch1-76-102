package configurations;

import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import entity.User;

@org.springframework.context.annotation.Configuration
@PropertySource("classpath:application.properties")
public class HibernateConfigurator {

    @Autowired
    private Environment env;

    @Bean
    public SessionFactory hibernateFactory() {
        Configuration config = new Configuration();

        Properties props = new Properties();
        props.setProperty("hibernate.connection.driver_class", env.getProperty("hibernate.connection.driver_class"));
        props.setProperty("hibernate.connection.url", env.getProperty("hibernate.connection.url"));
        props.setProperty("hibernate.connection.username", env.getProperty("hibernate.connection.username"));
        props.setProperty("hibernate.connection.password", env.getProperty("hibernate.connection.password"));
        props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        props.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        props.setProperty("hibernate.show_sql", "true");

        config.setProperties(props);
        config.addAnnotatedClass(User.class);

        return config.buildSessionFactory();
    }
}
