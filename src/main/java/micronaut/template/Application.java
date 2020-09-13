package micronaut.template;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@OpenAPIDefinition(
        info = @Info(
                title = "Micronaut Template",
                version = "1",
                description = "Micronaut Template Description",
                contact = @Contact(url = "https://github.com/GoodforGod", name = "Anton Kurako", email = "goodforgod.dev@gmail.com"),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0")),
        servers = {
                @Server(url = "http://localhost:8080", description = "Local Server")
        })
public class Application {

    static {
        final String buildNumber = Application.class.getPackage().getImplementationVersion();
        System.setProperty("BUILD_NUMBER", Optional.ofNullable(buildNumber).orElse("1"));
    }

    public static void main(String[] args) {
        final Logger logger = LoggerFactory.getLogger(Application.class);
        System.getenv().forEach((k, v) -> logger.debug("{}={}", k, v));
        Micronaut.run(Application.class);
    }
}
