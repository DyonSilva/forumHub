package dyon.forumhub.forum.topico;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record DadosCadastroTopico(


        Long id,
        @NotBlank
        String titulo,
        @NotBlank
        String mensagem,
        LocalDate data,
        @NotBlank
        String estado,
        @NotBlank
        String autor,
        @NotBlank
        String curso) {
}
