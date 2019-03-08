package mx.tiendas3b.oauth2;

import java.security.KeyPair;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
@EnableAuthorizationServer
public class AuthorizationSecurityConfigurer extends AuthorizationServerConfigurerAdapter {

	private AuthenticationManager authenticationManager;


	private Oauth2Properties oauth2Properties;

	@Autowired
	public AuthorizationSecurityConfigurer(AuthenticationManager authenticationManager, Oauth2Properties oauth2Properties) {
		this.authenticationManager = authenticationManager;

		this.oauth2Properties = oauth2Properties;
	}

	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource(oauth2Properties.getKeystoreJksFile()),
				oauth2Properties.getKeystoreJksPassword().toCharArray())
						.getKeyPair(oauth2Properties.getKeystoreJksKeypair());
		converter.setKeyPair(keyPair);
		return converter;
	}

	@Bean
	public JwtTokenStore tokenStore() {
		JwtTokenStore store = new JwtTokenStore(jwtAccessTokenConverter());
		return store;
	}

	@Bean
	@Primary
	public ResourceServerTokenServices defaultTokenServices() {
		final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenEnhancer((TokenEnhancer) jwtAccessTokenConverter());
		defaultTokenServices.setTokenStore(tokenStore());
		return defaultTokenServices;
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager).accessTokenConverter(jwtAccessTokenConverter())
				.tokenEnhancer((TokenEnhancer) jwtAccessTokenConverter()).exceptionTranslator(e -> {
					if (e instanceof OAuth2Exception) {
						OAuth2Exception oAuth2Exception = (OAuth2Exception) e;
						return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
								.body(new CustomAuthenticationException(oAuth2Exception.getMessage()));
					}
					throw e;
				});
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients
			.inMemory()
			.withClient("client")
			.secret("secret")
			.scopes("all")
			.authorizedGrantTypes("password", "refresh_token")
			.accessTokenValiditySeconds(oauth2Properties.getAccessTokenValiditySeconds())
			.refreshTokenValiditySeconds(oauth2Properties.getRefreshTokenValiditySeconds());
	}

	@Bean
	public WebResponseExceptionTranslator loggingExceptionTranslator() {
		return new DefaultWebResponseExceptionTranslator() {
			@Override
			public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
				// This is the line that prints the stack trace to the log. You can customise
				// this to format the trace etc if you like
				e.printStackTrace();

				// Carry on handling the exception
				ResponseEntity<OAuth2Exception> responseEntity = super.translate(e);
				HttpHeaders headers = new HttpHeaders();
				headers.setAll(responseEntity.getHeaders().toSingleValueMap());
				OAuth2Exception excBody = responseEntity.getBody();
				return new ResponseEntity<>(excBody, headers, responseEntity.getStatusCode());
			}
		};
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.checkTokenAccess("permitAll()");
	}

}