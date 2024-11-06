package sistemaGestao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sistemaGestao.entity.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

}