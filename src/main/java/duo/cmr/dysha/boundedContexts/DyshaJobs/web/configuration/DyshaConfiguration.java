package duo.cmr.dysha.boundedContexts.DyshaJobs.web.configuration;


import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshajob.DyshaJob;
import duo.cmr.dysha.boundedContexts.generalresearch.MyGeneralSearcher;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@org.springframework.context.annotation.Configuration
@AllArgsConstructor
@EnableWebSecurity
public class DyshaConfiguration {

    @Bean
    public MyGeneralSearcher<DyshaJob> getSearcher(){
        return new MyGeneralSearcher<DyshaJob>();
    }

}
