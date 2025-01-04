package sistemaGestao.entity;

import jakarta.persistence.*;
import java.util.List;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private String cargo;
    private String telefone;
    private String email;
    private String cpf;
    private LocalDate dataNascimento;
    private String endereco;
    private String bairro;
    private int numero;
    private String cidade;


}