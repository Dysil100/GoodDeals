package duo.cmr.dysha.boundedContexts.DyshaJobs.web.configuration;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class DyshaErrorConfig implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        ErrorPage errorPage = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error.html");
        factory.addErrorPages(errorPage);
    }
}
