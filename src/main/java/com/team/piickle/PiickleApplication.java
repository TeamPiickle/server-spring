package com.team.piickle;

import com.team.piickle.auth.jwt.TokenProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
@EnableConfigurationProperties({TokenProvider.class})
public class PiickleApplication {

    static {
        System.setProperty("com.amazonaws.sdk.disableEc2MetaData", "true");
    }

    public static void main(String[] args) {
        SpringApplication.run(PiickleApplication.class, args);
    }
}
