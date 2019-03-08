package mx.tiendas3b.oauth2;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "oauth2")
public class Oauth2Properties {

	private String keystoreJksFile;
	private String keystoreJksPassword;
	private String keystoreJksKeypair;

	private String buzoneLogin;
	private String buzoneChangePassword;
	private String buzoneObtenerUsuario;
	private String buzoneRecoverPassword;
	private String buzoneObtenerImagenUsuario;
	private String buzoneActualizarImagenUsuario;
	private String buzoneActualizarUsuario;

	public String getBuzoneActualizarImagenUsuario() {
		return buzoneActualizarImagenUsuario;
	}

	public void setBuzoneActualizarImagenUsuario(String buzoneActualizarImagenUsuario) {
		this.buzoneActualizarImagenUsuario = buzoneActualizarImagenUsuario;
	}

	public String getBuzoneObtenerImagenUsuario() {
		return buzoneObtenerImagenUsuario;
	}

	public void setBuzoneObtenerImagenUsuario(String buzoneObtenerImagenUsuario) {
		this.buzoneObtenerImagenUsuario = buzoneObtenerImagenUsuario;
	}

	private Integer accessTokenValiditySeconds;
	private Integer refreshTokenValiditySeconds;

	private String ldapUsername;
	private String ldapUrl;
	private String ldapPassword;
	private String ldapBase;

	private String ldapClaveServicio;

	public String getKeystoreJksFile() {
		return keystoreJksFile;
	}

	public void setKeystoreJksFile(String keystoreJksFile) {
		this.keystoreJksFile = keystoreJksFile;
	}

	public String getKeystoreJksPassword() {
		return keystoreJksPassword;
	}

	public void setKeystoreJksPassword(String keystoreJksPassword) {
		this.keystoreJksPassword = keystoreJksPassword;
	}

	public String getKeystoreJksKeypair() {
		return keystoreJksKeypair;
	}

	public void setKeystoreJksKeypair(String keystoreJksKeypair) {
		this.keystoreJksKeypair = keystoreJksKeypair;
	}

	public String getBuzoneLogin() {
		return buzoneLogin;
	}

	public void setBuzoneLogin(String buzoneLogin) {
		this.buzoneLogin = buzoneLogin;
	}

	public String getBuzoneChangePassword() {
		return buzoneChangePassword;
	}

	public void setBuzoneChangePassword(String buzoneChangePassword) {
		this.buzoneChangePassword = buzoneChangePassword;
	}

	public String getBuzoneObtenerUsuario() {
		return buzoneObtenerUsuario;
	}

	public void setBuzoneObtenerUsuario(String buzoneObtenerUsuario) {
		this.buzoneObtenerUsuario = buzoneObtenerUsuario;
	}

	public String getBuzoneRecoverPassword() {
		return buzoneRecoverPassword;
	}

	public void setBuzoneRecoverPassword(String buzoneRecoverPassword) {
		this.buzoneRecoverPassword = buzoneRecoverPassword;
	}

	public Integer getAccessTokenValiditySeconds() {
		return accessTokenValiditySeconds;
	}

	public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
		this.accessTokenValiditySeconds = accessTokenValiditySeconds;
	}

	public Integer getRefreshTokenValiditySeconds() {
		return refreshTokenValiditySeconds;
	}

	public void setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds) {
		this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
	}

	public String getLdapUsername() {
		return ldapUsername;
	}

	public void setLdapUsername(String ldapUsername) {
		this.ldapUsername = ldapUsername;
	}

	public String getLdapUrl() {
		return ldapUrl;
	}

	public void setLdapUrl(String ldapUrl) {
		this.ldapUrl = ldapUrl;
	}

	public String getLdapPassword() {
		return ldapPassword;
	}

	public void setLdapPassword(String ldapPassword) {
		this.ldapPassword = ldapPassword;
	}

	public String getLdapBase() {
		return ldapBase;
	}

	public void setLdapBase(String ldapBase) {
		this.ldapBase = ldapBase;
	}

	public String getLdapClaveServicio() {
		return ldapClaveServicio;
	}

	public void setLdapClaveServicio(String ldapClaveServicio) {
		this.ldapClaveServicio = ldapClaveServicio;
	}

	public String getBuzoneActualizarUsuario() {
		return buzoneActualizarUsuario;
	}

	public void setBuzoneActualizarUsuario(String buzoneActualizarUsuario) {
		this.buzoneActualizarUsuario = buzoneActualizarUsuario;
	}

}
