package br.com.fiap3espb.auto_escola_3espb.domain.usuario;

import br.com.fiap3espb.auto_escola_3espb.domain.usuario.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder) {
        service.cadastrar(dados);
        var uri = uriBuilder.path("/usuarios/{id}").build().toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoUsuario>> listar(@PageableDefault(size = 10, sort = {"login"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosDetalhamentoUsuario::new);
        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/senha")
    @Transactional
    public ResponseEntity alterarSenha(@RequestBody @Valid DadosAlteracaoSenha dados) {
        service.alterarSenha(dados);
        return ResponseEntity.ok().build();
    }
}