package sistemaGestao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sistemaGestao.entity.Produto;
import sistemaGestao.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public String save(Produto produto) {
		this.produtoRepository.save(produto);
		return  "Cadastrado com sucesso!";
	}
	
	public String update(int id, Produto produto) {
		produto.setId(id);
		this.produtoRepository.save(produto);
		return produto.getNome_produto()+" atualizado com sucesso!";
	}
	
	public List<Produto> findAll(){
		return this.produtoRepository.findAll();
	}
	
	public Produto findById(long idProduto) {
		Produto produto = this.produtoRepository.findById(idProduto).get();
		return produto;
	}
	
	public String delete(long idProduto) {
		this.produtoRepository.deleteById(idProduto);
		return "Produto deletado com sucesso!"; 
	}
}
