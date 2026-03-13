package br.com.fiap3espb.auto_escola_3espb.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosAlteracaoSenha(
        @NotBlank String novaSenha
) {}
