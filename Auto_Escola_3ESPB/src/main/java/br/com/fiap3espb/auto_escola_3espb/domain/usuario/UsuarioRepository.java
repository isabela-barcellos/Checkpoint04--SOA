package br.com.fiap3espb.auto_escola_3espb.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
     // O Spring Security utiliza UserDetails para o processo de autenticação
     UserDetails findByLogin(String login);
}