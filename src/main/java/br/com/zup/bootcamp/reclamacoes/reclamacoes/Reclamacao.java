package br.com.zup.bootcamp.reclamacoes.reclamacoes;

import br.com.zup.bootcamp.reclamacoes.util.TelefoneUtil;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Table(uniqueConstraints = @UniqueConstraint(name = "UK_texto_e_telefone_usuario",columnNames = {"telefoneUsuario","texto"}))
@Entity
public class Reclamacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeUsuario;

    @Column(nullable = false)
    private String emailUsuario;

    @Column(nullable = false, length = 64)
    private String telefoneUsuario;

    @Column(nullable = false,length = 4000)
    private String texto;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDate dataRegistro;

    public Reclamacao(String nomeUsuario, String emailUsuario, String telefoneUsuario, String texto, LocalDate dataRegistro) {
        this.nomeUsuario = nomeUsuario;
        this.emailUsuario = emailUsuario;
        this.telefoneUsuario = TelefoneUtil.hash(telefoneUsuario);
        this.texto = texto;
        this.dataRegistro = dataRegistro;
    }

    @Deprecated // hibernate
    public Reclamacao() {
    }

    public Long getId() {
        return id;
    }
}
