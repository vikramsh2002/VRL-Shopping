package vrl.VRL_EShopping.MongoTempConfig;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collection;
import java.util.Collections;


@EnableMongoRepositories( basePackages = "vrl.VRL_EShopping")
@Configuration
public class mongoConfig extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName(){
        return "shopping";
    }
    @Override
    public MongoClient mongoClient(){ // Generate mongo client
        // setup the connection uri
        final ConnectionString con=new ConnectionString("mongodb://localhost:27017/shopping");

        // Takes the con and put it in mongo client and then build it by the builder
        final MongoClientSettings mongoClentSettings;
        mongoClentSettings= MongoClientSettings.builder().applyConnectionString(con).build();


        // Create the clients from settings
        return MongoClients.create(mongoClentSettings);
    }

    @Override
    public Collection<String> getMappingBasePackages(){

        // Collection is an interface
        // Collections is the class
        return Collections.singleton("vrl.VRL_EShopping");
    }

    @Bean
    public MongoTemplate mongoTemplate(){
        MongoTemplate mt= new MongoTemplate(mongoDbFactory());
        return mt;
    }







}
