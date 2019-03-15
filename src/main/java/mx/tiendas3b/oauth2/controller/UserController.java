package mx.tiendas3b.oauth2.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.tiendas3b.oauth2.Usuario;
import mx.tiendas3b.oauth2.UsuarioRepository;

@RestController
public class UserController {

	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	private JwtAccessTokenConverter jwtAccessTokenConverter;
	private UsuarioRepository usuarioRepository;
	private ResourceServerTokenServices resourceServerTokenServices;

	@Autowired
	public UserController(JwtAccessTokenConverter jwtAccessTokenConverter, UsuarioRepository usuarioRepository,
			ResourceServerTokenServices resourceServerTokenServices) {
		this.jwtAccessTokenConverter = jwtAccessTokenConverter;
		this.usuarioRepository = usuarioRepository;
		this.resourceServerTokenServices = resourceServerTokenServices;
	}



	@RequestMapping("/user")
	public Usuario user(@RequestHeader("Authorization") String value) {

		String tokenValue = value.split(" ")[1];
		LOG.info("Authorization user Token {}", tokenValue);
		OAuth2AccessToken token = resourceServerTokenServices.readAccessToken(tokenValue);
		if (token == null) {
			throw new InvalidTokenException("Token was not recognised");
		}

		if (token.isExpired()) {
			throw new InvalidTokenException("Token has expired");
		}

		OAuth2Authentication authentication = resourceServerTokenServices.loadAuthentication(token.getValue());

		Map<String, ?> response = jwtAccessTokenConverter.convertAccessToken(token, authentication);

		return usuarioRepository.findByLogin(response.get("user_name").toString());
	}

}
