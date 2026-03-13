package br.com.fiap3espb.auto_escola_3espb.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Injetado a partir da sua configuração de segurança

    @Transactional
    public void cadastrar(DadosCadastroUsuario dados) {
        // Encripta a senha antes de salvar
        String senhaCriptografada = passwordEncoder.encode(dados.senha());

        // Usa o novo construtor da entidade Usuario que criamos no passo anterior
        var usuario = new Usuario(dados, senhaCriptografada);

        repository.save(usuario);
    }

    @Transactional
    public void alterarSenha(DadosAlteracaoSenha dados) {
        Usuario usuarioLogado = repository.getReferenceById(dados.id());
        // Encripta a nova senha e atualiza o objeto do usuário logado
        String novaSenhaCriptografada = passwordEncoder.encode(dados.novaSenha());
        usuarioLogado.atualizarSenha(novaSenhaCriptografada);
        repository.save(usuarioLogado);
    }
}