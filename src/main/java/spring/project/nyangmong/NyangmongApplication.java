package spring.project.nyangmong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class NyangmongApplication {

	public static void main(String[] args) {
		SpringApplication.run(NyangmongApplication.class, args);
	}
}