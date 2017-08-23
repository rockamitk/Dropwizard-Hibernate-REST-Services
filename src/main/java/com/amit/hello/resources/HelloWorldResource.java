package com.amit.hello.resources;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.hibernate.SessionFactory;

import com.amit.hello.helloWorldConfiguration;
import com.codahale.metrics.annotation.Timed;


@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
	private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public HelloWorldResource(SessionFactory sessionFactory, helloWorldConfiguration configuration) {
    	this.template = configuration.getTemplate();
        this.defaultName = configuration.getDefaultName();
        this.counter = new AtomicLong();
    }

//    @GET
//    @Timed
//    public SayingObject sayHello(@QueryParam("name") Optional<String> name) {
//        final String value = String.format(template, name.orElse(defaultName));
//        return new SayingObject(counter.incrementAndGet(), value);
//    }
}
