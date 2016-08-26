package cs544.project.share2care;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;

@SpringBootApplication (exclude={SecurityAutoConfiguration.class})
public class Share2careApplication {

	public static void main(String[] args) {
		SpringApplication.run(Share2careApplication.class, args);
		System.out.println("hello");
	}
}
