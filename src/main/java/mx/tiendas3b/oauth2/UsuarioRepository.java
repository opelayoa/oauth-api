package mx.tiendas3b.oauth2;

import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
	
	Usuario findByLogin(String login);
}
