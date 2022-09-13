package br.com.zup.bootcamp.reclamacoes.reclamacoes;

import br.com.zup.bootcamp.reclamacoes.util.TelefoneUtil;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

public class ReclamacoesRequest {

    @NotBlank
    private String nomeUsuario;
    @NotBlank
    @Email
    private String emailUsuario;
    @NotNull
    @Pattern(regexp = "^\\+[1-9][0-9]\\d{1,14}$")
    private String telefoneUsuario;

    @NotBlank
    @Length(max = 4000)
    private String texto;

    private LocalDate dataRegistro;

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public String getTelefoneUsuario() {
        return telefoneUsuario;
    }

    public String getTexto() {
        return texto;
    }

    public LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public String getHashTelefone(){
        return TelefoneUtil.hash(telefoneUsuario);
    }

    public Reclamacao toModel() {
        return new Reclamacao(nomeUsuario,emailUsuario,telefoneUsuario,texto,dataRegistro);
    }
}
