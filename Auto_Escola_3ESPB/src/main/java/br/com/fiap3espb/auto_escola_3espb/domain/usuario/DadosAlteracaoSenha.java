package br.com.fiap3espb.auto_escola_3espb.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAlteracaoSenha(
        @NotNull Long id,
        @NotBlank String novaSenha
) {}
