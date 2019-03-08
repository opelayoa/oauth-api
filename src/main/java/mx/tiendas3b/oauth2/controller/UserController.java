package mx.tiendas3b.oauth2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	private JwtAccessTokenConverter jwtAccessTokenConverter;
	private ResourceServerTokenServices resourceServerTokenServices;

	@Autowired
	public UserController(JwtAccessTokenConverter jwtAccessTokenConverter,
			ResourceServerTokenServices resourceServerTokenServices) {
		this.jwtAccessTokenConverter = jwtAccessTokenConverter;
		this.resourceServerTokenServices = resourceServerTokenServices;
	}

}
