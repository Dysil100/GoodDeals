package duo.cmr.deuxKolos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
public class DeuxKolosJdbcOauthEmailApplication {
    // TODO: 03.02.22 Fetch all data in an .excel oder .csv file while server ist running

    public static void main(String[] args) {
        SpringApplication.run(DeuxKolosJdbcOauthEmailApplication.class, args);
    }

}
