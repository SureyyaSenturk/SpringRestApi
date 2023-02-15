import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Süreyya
 * @since 0.0.1
 * @version 0.0.1
 */

@SpringBootApplication
@ComponentScan(basePackages = {"controller","repository","service","entity"})
@EnableJpaRepositories(basePackages = "repository")
@EnableAutoConfiguration
@EnableWebMvc
@EntityScan(basePackages = {"entity"})

public class App {

    public static void main(String[] args) {

         SpringApplication.run(App.class,args);

    }
}
