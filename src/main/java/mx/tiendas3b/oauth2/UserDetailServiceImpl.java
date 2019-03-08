package mx.tiendas3b.oauth2;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = usuarioRepository.findByLogin(username);

		if(usuario == null) {
			throw new UsernameNotFoundException("User does not exist.");
		}
		User userDetails = new User(new ArrayList<GrantedAuthority>(), usuario.getPassword(), usuario.getLogin(), true,
				true, true, true);
		
		return userDetails;
	}

}
