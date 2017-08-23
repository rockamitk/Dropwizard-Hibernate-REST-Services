package com.amit.hello;

import com.amit.hello.resources.HelloWorldResource;
import com.amit.hello.resources.ProductResource;

import db.tables.Product;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class helloWorldApplication extends Application<helloWorldConfiguration> {

    public static void main(final String[] args) throws Exception {
        new helloWorldApplication().run(args);
    }

    private final HibernateBundle<helloWorldConfiguration> hibernate = new HibernateBundle<helloWorldConfiguration>(Product.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(helloWorldConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };
    
    @Override
    public void initialize(final Bootstrap<helloWorldConfiguration> bootstrap) {
    	bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(final helloWorldConfiguration configuration, final Environment environment) {
    	//final DBIFactory factory = new DBIFactory();
        //final DBI jdbi = factory.build(environment, config.getDataSourceFactory(), "postgresql");
    	final HelloWorldResource helloWorldResource = new HelloWorldResource(this.hibernate.getSessionFactory(), configuration);
    	final ProductResource productResource = new ProductResource(this.hibernate.getSessionFactory(), configuration);
    	environment.jersey().register(helloWorldResource);
    	environment.jersey().register(productResource);
    }
    
    @Override
    public String getName() {
        return "helloWorld";
    }
}