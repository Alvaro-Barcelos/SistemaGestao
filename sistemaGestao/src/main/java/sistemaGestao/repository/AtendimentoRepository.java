package sistemaGestao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sistemaGestao.entity.Atendimento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {

    // Usando a função CAST para garantir que apenas a parte da data seja considerada
    @Query("SELECT a FROM Atendimento a WHERE CAST(a.data_atendimento AS date) BETWEEN :inicio AND :fim")
    List<Atendimento> findByDataAtendimentoBetween(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);
}
