package sistemaGestao.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import sistemaGestao.entity.Cliente;

import sistemaGestao.repository.ClienteRepository;


@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public String save(Cliente cliente) {
		this.clienteRepository.save(cliente);
		return  "Cadastrado com sucesso!";
	}
	
	public String update(int id, Cliente cliente) {
		cliente.setId(id);
		this.clienteRepository.save(cliente);
		return cliente.getNome()+" atualizado com sucesso!";
	}
	
	public List<Cliente> findAll(){

        return this.clienteRepository.findAll(Sort.by(Sort.Order.asc("nome")));
	}
	
	public Cliente findById(long id) {
		Cliente cliente = this.clienteRepository.findById(id).get();
		return cliente;
	}
	
	public String delete(long id) {
		this.clienteRepository.deleteById(id);
		return "Produto deletado com sucesso!"; 
	}
}
