package duo.cmr.dysha.boundedContexts.dasandere.web.securityconfiguration;

import duo.cmr.dysha.boundedContexts.dasandere.domain.model.RegistrationRequest;
import duo.cmr.dysha.boundedContexts.generalhelpers.matchers.MyMatchValidator;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@org.springframework.context.annotation.Configuration
@AllArgsConstructor
@EnableWebSecurity
public class DasAndereConfiguration {
    @Bean
    public MyMatchValidator<RegistrationRequest> getRegistrationRequestValidator(){
        return new MyMatchValidator<RegistrationRequest>();
    }
}
