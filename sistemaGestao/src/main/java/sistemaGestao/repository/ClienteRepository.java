package sistemaGestao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sistemaGestao.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
