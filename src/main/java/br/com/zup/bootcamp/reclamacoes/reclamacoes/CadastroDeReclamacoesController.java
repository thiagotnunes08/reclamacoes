package br.com.zup.bootcamp.reclamacoes.reclamacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/reclamacoes")
public class CadastroDeReclamacoesController {
    @Autowired
    private final ReclamacaoRepository repository;

    public CadastroDeReclamacoesController(ReclamacaoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastra(@Valid @RequestBody ReclamacoesRequest request, UriComponentsBuilder uriComponentsBuilder){

        if (repository.existsByTelefoneUsuarioOrTexto(request.getHashTelefone(),request.getTexto())){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,"essa reclamação já foi aberta por esse telefone,favor aguardar!");
        }

        Reclamacao novaReclamacao = request.toModel();

        repository.save(novaReclamacao);

        URI location = uriComponentsBuilder.path("/reclamacoes/{id}").buildAndExpand(novaReclamacao.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    /**
     * @ExceptionHandler = restcontroller advice local
     */
     @ExceptionHandler(ConstraintViolationException.class)
     public ResponseEntity<?> nossoHandle(ConstraintViolationException ex){

         Map<String,Object> body = Map.of("menssagem:","essa reclamação já foi aberta por esse telefone,favor aguardar!",
                                            "ocorreu em:", LocalDateTime.now(),
                                            "status code:","422");

         return ResponseEntity.unprocessableEntity().body(body);
     }
}
