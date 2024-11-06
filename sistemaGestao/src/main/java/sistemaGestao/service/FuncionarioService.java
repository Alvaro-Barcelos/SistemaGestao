package sistemaGestao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sistemaGestao.entity.Funcionario;

import sistemaGestao.repository.FuncionarioRepository;

@Service
public class FuncionarioService{

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	public String save(Funcionario funcionario) {
		this.funcionarioRepository.save(funcionario);
		return  "Cadastrado com sucesso!";
	}
	
	public String update(int id, Funcionario funcionario) {
		funcionario.setId(id);
		this.funcionarioRepository.save(funcionario);
		return funcionario.getNome()+" atualizado com sucesso!";
	}
	
	public List<Funcionario> findAll(){
		return this.funcionarioRepository.findAll();
	}
	
	public Funcionario findById(long id) {
		Funcionario funcionario = this.funcionarioRepository.findById(id).get();
		return funcionario;
	}
	
	public String delete(long id) {
		this.funcionarioRepository.deleteById(id);
		return "Produto deletado com sucesso!"; 
	}
}
