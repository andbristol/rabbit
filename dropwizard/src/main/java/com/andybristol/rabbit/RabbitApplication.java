package com.andybristol.rabbit;

import com.andybristol.rabbit.jdbi.DAO;
import com.andybristol.rabbit.resources.BunResource;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

public class RabbitApplication extends Application<RabbitConfiguration> {

    public static void main(String[] args) throws Exception{
        new RabbitApplication().run(args);
    }

    @Override
    public void run(RabbitConfiguration configuration, Environment environment) throws Exception {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");
        final DAO dao = jdbi.onDemand(DAO.class);
        environment.jersey().register(new BunResource(dao));
    }
}
