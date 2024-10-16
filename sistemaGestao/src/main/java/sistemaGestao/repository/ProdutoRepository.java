package sistemaGestao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sistemaGestao.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	

}
