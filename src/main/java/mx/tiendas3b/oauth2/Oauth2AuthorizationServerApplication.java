package mx.tiendas3b.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication(scanBasePackages = { "mx.tiendas3b.oauth2" })
@CrossOrigin("*")
@EnableResourceServer
@RestController
public class Oauth2AuthorizationServerApplication extends WebMvcConfigurerAdapter {

	@Autowired
	public Oauth2AuthorizationServerApplication() {

	}

	public static void main(String[] args) {
		SpringApplication.run(Oauth2AuthorizationServerApplication.class, args);
	}

	@Bean
	public WebAppSecurityConfig webAppSecurityConfig() {
		return new WebAppSecurityConfig();
	}

	@Order(1)
	protected static class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.csrf().disable().exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint());

		}
	}

}
