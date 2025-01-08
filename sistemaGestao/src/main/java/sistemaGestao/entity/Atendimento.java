package sistemaGestao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Atendimento {

	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long id;

	    // Chaves estrangeiras
	    @ManyToOne
	    @JoinColumn(name = "id_funcionario")
	    private Funcionario funcionario;

	    @ManyToOne
	    @JoinColumn(name = "id_cliente")
	    private Cliente cliente;

	    @ManyToOne
	    @JoinColumn(name = "id_tipo_atendimento")
	    private TipoAtendimento tipo_atendimento;

	    // Alterando o tipo para LocalDateTime
	    private LocalDate data_atendimento;  // Agora é LocalDateTime

	    private LocalTime hora_atendimento;
	    private String observacao;
	
}
