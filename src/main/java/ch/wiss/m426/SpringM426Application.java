package ch.wiss.m426;

import ch.wiss.m426.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class SpringM426Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringM426Application.class, args);
	}
}
