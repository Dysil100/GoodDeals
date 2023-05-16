package duo.cmr.dysha;

import duo.cmr.dysha.boundedContexts.dasandere.web.services.subservices.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class DeuxKolosJdbcOauthEmailApplication {
    // TODO: 03.02.22 Fetch all data in an .excel oder .csv file while server ist running

    public static void main(String[] args) {
        SpringApplication.run(DeuxKolosJdbcOauthEmailApplication.class, args);
    }

    /*@Autowired
   private EmailService emailService;
    @EventListener(ApplicationReadyEvent.class)
    public void sendMail(){
        emailService.send("silatsamdylan@gmail.com", "test", "Yo bro que ca a marché! cest nice");
    }*/
}
