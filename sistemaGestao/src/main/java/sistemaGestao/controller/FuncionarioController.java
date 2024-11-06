package sistemaGestao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sistemaGestao.entity.Funcionario;
import sistemaGestao.service.FuncionarioService;

@RestController
@RequestMapping("/funcionario")
@CrossOrigin("http://localhost:4200")
public class FuncionarioController{
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Funcionario funcionario){
		
		try{
			String mensagem = this.funcionarioService.save(funcionario);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity<>("Algo deu errado!!" + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> update (@RequestBody Funcionario funcionario, @PathVariable int id){
		try {
			String mensagem = this.funcionarioService.update(id, funcionario);
			return new ResponseEntity<String>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Algo deu errado!! " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Funcionario>> findAll(){
		try{
			List<Funcionario> lista = this.funcionarioService.findAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Funcionario> findById(@PathVariable int id){
		try{
			Funcionario funcionario = this.funcionarioService.findById(id);
			return new ResponseEntity<>(funcionario, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable long id){
		try{
			String mensagem = this.funcionarioService.delete(id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<String>("Algo deu errado ao deletar! " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
