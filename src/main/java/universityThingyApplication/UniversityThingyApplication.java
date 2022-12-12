package universityThingyApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

//@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@SpringBootApplication
public class UniversityThingyApplication implements CommandLineRunner {

	@Autowired
	JdbcTemplate jdbcTemplate;
	public static void main(String[] args) {
		System.out.println("HI there");
		SpringApplication.run(UniversityThingyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String sql = "ALTER SESSION SET CONTAINER=XEPDB1";
		jdbcTemplate.execute(sql);
		System.out.println("Must have switched to XEPDB1 database");
		
	}

}
