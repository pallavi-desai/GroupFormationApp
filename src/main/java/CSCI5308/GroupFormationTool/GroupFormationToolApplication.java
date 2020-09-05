package CSCI5308.GroupFormationTool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GroupFormationToolApplication {

	static final Logger logger = LoggerFactory.getLogger(GroupFormationToolApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GroupFormationToolApplication.class, args);
	}
}
