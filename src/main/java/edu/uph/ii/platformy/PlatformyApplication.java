package edu.uph.ii.platformy;

import edu.uph.ii.platformy.controllers.commands.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class PlatformyApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlatformyApplication.class, args);
	}
}
