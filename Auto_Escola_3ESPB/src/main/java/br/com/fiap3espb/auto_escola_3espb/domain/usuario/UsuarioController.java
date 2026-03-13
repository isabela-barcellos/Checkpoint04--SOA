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
@SecurityRequirement(name = "bearer-key") // [cite: 233]
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private UsuarioRepository repository;

    @PostMapping // [cite: 13]
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder) {
        service.cadastrar(dados);
        var uri = uriBuilder.path("/usuarios/{id}").build().toUri(); // [cite: 124]
        return ResponseEntity.created(uri).build(); // [cite: 121]
    }

    @GetMapping // [cite: 14]
    public ResponseEntity<Page<DadosDetalhamentoUsuario>> listar(@PageableDefault(size = 10, sort = {"login"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosDetalhamentoUsuario::new); // [cite: 86, 120]
        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{id}") // [cite: 16]
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build(); // [cite: 116]
    }

    @PutMapping("/alterar-senha") //
    @Transactional
    public ResponseEntity alterarSenha(@RequestBody @Valid DadosAlteracaoSenha dados, @AuthenticationPrincipal Usuario logado) {
        service.alterarSenha(dados, logado);
        return ResponseEntity.ok().build(); // [cite: 117]
    }
}