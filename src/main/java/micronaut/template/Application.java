package micronaut.template;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@OpenAPIDefinition(
        info = @Info(
                title = "micronaut-template",
                version = "1",
                description = "micronaut-template-description",
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
        final Collection<String> expected = List.of("SECURITY_ENABLED");

        System.getenv().entrySet().stream()
                .filter(e -> expected.contains(e.getKey()))
                .forEach(e -> logger.debug("{}={}", e.getKey(), e.getValue()));
        Micronaut.run(Application.class);
    }
}
