package sistemaGestao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sistemaGestao.entity.Atendimento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {

	@Query("SELECT a FROM Atendimento a WHERE a.funcionario.id = :funcionarioId AND a.data_atendimento = :dataAtendimento")
	List<Atendimento> buscarAtendimentosPorFuncionarioEData(
	        @Param("funcionarioId") Long funcionarioId, 
	        @Param("dataAtendimento") LocalDate dataAtendimento);

	@Query("SELECT COUNT(a) > 0 FROM Atendimento a WHERE a.funcionario.id = :funcionarioId AND a.data_atendimento = :dataAtendimento AND a.hora_atendimento = :horaAtendimento")
	boolean verificarHorarioOcupado(
	        @Param("funcionarioId") Long funcionarioId, 
	        @Param("dataAtendimento") LocalDate dataAtendimento, 
	        @Param("horaAtendimento") LocalTime horaAtendimento);

	@Query("SELECT a FROM Atendimento a WHERE a.data_atendimento BETWEEN :inicio AND :fim")
	List<Atendimento> buscarAtendimentosPorIntervalo(
	        @Param("inicio") LocalDate inicio, 
	        @Param("fim") LocalDate fim);
	
    @Query("SELECT COUNT(a) FROM Atendimento a WHERE a.funcionario.id = :funcionarioId AND YEAR(a.data_atendimento) = :ano AND MONTH(a.data_atendimento) = :mes")
    long countByFuncionarioAndMonth(@Param("funcionarioId") Long funcionarioId, @Param("ano") int ano, @Param("mes") int mes);

    @Query("SELECT COUNT(a) FROM Atendimento a WHERE a.cliente.id = :clienteId AND YEAR(a.data_atendimento) = :ano AND MONTH(a.data_atendimento) = :mes")
    long countByClienteAndMonth(@Param("clienteId") Long clienteId, @Param("ano") int ano, @Param("mes") int mes);
    

}

