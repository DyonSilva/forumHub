package dyon.forumhub.forum.topico;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime data;
    private String estado;
    private String autor;
    private String curso;

    public Topico(DadosCadastroTopico dadosCadastroTopico) {
        this.titulo = dadosCadastroTopico.titulo();
        this.mensagem = dadosCadastroTopico.mensagem();
        this.data = LocalDateTime.now();
        this.estado = dadosCadastroTopico.estado();
        this.autor = dadosCadastroTopico.autor();
        this.curso = dadosCadastroTopico.curso();



    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
