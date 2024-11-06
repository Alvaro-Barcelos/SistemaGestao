package sistemaGestao.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.transaction.Transactional;
import sistemaGestao.entity.Cliente;
import sistemaGestao.entity.Funcionario;
import sistemaGestao.entity.Produto;
import sistemaGestao.entity.VendaProduto;
import sistemaGestao.repository.ClienteRepository;
import sistemaGestao.repository.FuncionarioRepository;
import sistemaGestao.repository.ProdutoRepository;
import sistemaGestao.repository.VendaProdutoRepository;

@Service
public class VendaProdutoService {
	
	 @Autowired
	    private VendaProdutoRepository vendaProdutoRepository;

	    @Autowired
	    private ProdutoRepository produtoRepository;

	    @Autowired
	    private ClienteRepository clienteRepository;  // Adicione este repositório para o Cliente

	    @Autowired
	    private FuncionarioRepository funcionarioRepository; // Adicione este repositório para o Funcionario

	    public String save(VendaProduto vendaProduto) {
	        Produto produto = produtoRepository.findById(vendaProduto.getProduto().getId())
	            .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));

	        // Buscar Cliente e Funcionario por ID
	        Cliente cliente = clienteRepository.findById(vendaProduto.getCliente().getId())
	            .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));

	        Funcionario funcionario = funcionarioRepository.findById(vendaProduto.getFuncionario().getId())
	            .orElseThrow(() -> new RuntimeException("Funcionário não encontrado!"));

	        // Verificar se há estoque suficiente
	        if (produto.getQuantidade() < vendaProduto.getQuantidade()) {
	            throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getId());
	        }

	        // Atualizar quantidade do produto
	        produto.setQuantidade(produto.getQuantidade() - vendaProduto.getQuantidade());
	        produtoRepository.save(produto);

	        // Atualizar a vendaProduto com as entidades completas
	        vendaProduto.setProduto(produto);
	        vendaProduto.setCliente(cliente);
	        vendaProduto.setFuncionario(funcionario);
	        vendaProduto.setData_venda(LocalDate.now());

	        // Salvar a venda
	        try {
	            VendaProduto vendaSalva = vendaProdutoRepository.save(vendaProduto);
	            return "Venda registrada com sucesso! ID: " + vendaSalva.getId();
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException("Erro ao salvar a venda: " + e.getMessage());
	        }
	    }
	
	
	public String update(int id, VendaProduto vendaProduto) {
		vendaProduto.setId(id);
		this.vendaProdutoRepository.save(vendaProduto);
		return vendaProduto.getId()+" atualizado com sucesso!";
	}
	
	public List<VendaProduto> findAll(){
		return this.vendaProdutoRepository.findAll();
	}
	
	public VendaProduto findById(long idVendaProduto) {
		VendaProduto vendaProduto = this.vendaProdutoRepository.findById(idVendaProduto).get();
		return vendaProduto;
	}
	
	public String delete(long idVendaProduto) {
		this.vendaProdutoRepository.deleteById(idVendaProduto);
		return "Produto deletado com sucesso!"; 
	}
}
