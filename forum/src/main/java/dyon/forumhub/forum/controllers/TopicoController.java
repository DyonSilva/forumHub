package dyon.forumhub.forum.controllers;

import dyon.forumhub.forum.topico.DadosCadastroTopico;
import dyon.forumhub.forum.topico.Topico;
import dyon.forumhub.forum.topico.TopicoRepository;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("topicos")
public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;
    private AtualizarTopicoDTO atualizarTopicoDTO;
    @PostMapping
    @Transactional
    public void cadastrarTopico( @RequestBody @Valid DadosCadastroTopico dadosCadastroTopico){
       topicoRepository.save(new Topico(dadosCadastroTopico));

    }

    @GetMapping
    public ResponseEntity<List<Topico>> listarTopicos(){
        List<Topico> topicos = topicoRepository.findAll();
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topico> detalharTopico(@PathVariable Long id) {

        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isPresent()){
            return ResponseEntity.ok(topicoOptional.get());

        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topico> atualizarTopico(@PathVariable Long id, @RequestBody AtualizarTopicoDTO atualizarTopicoDTO){

        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isPresent()){
            Topico topico = topicoOptional.get();

            if (atualizarTopicoDTO.titulo() != null){
                topico.setTitulo(atualizarTopicoDTO.titulo());
            }
            if (atualizarTopicoDTO.mensagem() != null){
                topico.setMensagem(atualizarTopicoDTO.mensagem());
            }
            if (atualizarTopicoDTO.autor() != null){
                topico.setAutor(atualizarTopicoDTO.autor());
            }
            if (atualizarTopicoDTO.curso() != null){
                topico.setCurso(atualizarTopicoDTO.curso());
            }

            Topico topicoAtualizado = topicoRepository.save(topico);
            return ResponseEntity.ok(topicoAtualizado);


        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirTopico(@PathVariable Long id){
        if (topicoRepository.existsById(id)){
            topicoRepository.deleteById(id);
            return ResponseEntity.ok("Tópico removido com sucesso");
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
