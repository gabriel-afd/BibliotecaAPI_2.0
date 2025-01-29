package livros.biblioteca.api.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("bearer-key", new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT ")))
                .info(new Info()
                        .title("biblioteca_API")
                        .description("API Rest da aplicação bibliotecaAPI, contendo funcionalidade de CRUD de Livros e Leitores, além do agendamento de reservas de livros")
                        .contact(new Contact()
                                .name("Gabriel Medeiros - Dev Back-End")
                                .email("gmedeiros144@gmail.com")));}


    }
