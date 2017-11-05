package hu.elte.alkfejl.config;

import hu.elte.alkfejl.model.*;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DatabaseConfig  {

    private static Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);

    @Autowired
    DataSource dataSource;

    @Bean
    LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setAnnotatedClasses(Topping.class, Pizza.class, User.class, Order.class, Dessert.class);
        Properties hibernateProperties = hibernateProperties();
        factoryBean.setHibernateProperties(hibernateProperties);
        return factoryBean;
    }

    @Autowired
    @Bean
    HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        return hibernateProperties;
    }
}