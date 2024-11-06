package sistemaGestao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sistemaGestao.entity.Cliente;
import sistemaGestao.entity.Funcionario;
import sistemaGestao.entity.Produto;
import sistemaGestao.entity.VendaProduto;

public interface VendaProdutoRepository extends JpaRepository<VendaProduto, Long> {

}


