package mx.tiendas3b.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
public class AuthenticationSecurityConfigurer extends GlobalAuthenticationConfigurerAdapter {

	private UserDetailServiceImpl userDetailServiceImpl;

	@Autowired
	public AuthenticationSecurityConfigurer(UserDetailServiceImpl userDetailServiceImpl) {
		this.userDetailServiceImpl = userDetailServiceImpl;
	}

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailServiceImpl).passwordEncoder(new Md5PasswordEncoder());

		super.init(auth);
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
	}
}