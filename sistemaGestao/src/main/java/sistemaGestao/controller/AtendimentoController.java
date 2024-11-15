package sistemaGestao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sistemaGestao.entity.Atendimento;
import sistemaGestao.service.AtendimentoService;

@RestController
@RequestMapping("/atendimento")
@CrossOrigin("http://localhost:4200")
public class AtendimentoController {

	@Autowired
	private AtendimentoService atendimentoService;
	
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Atendimento atendimento){
		try {
			String mensagem = this.atendimentoService.save(atendimento);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Algo deu errado ao salvar!" + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Atendimento>> findAll(){
		try {
			List<Atendimento> lista = this.atendimentoService.findAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findById/{idAtendimento}")
	public ResponseEntity<Atendimento> findById(@PathVariable int idAtendimento){
		try {
			Atendimento atendimento = this.atendimentoService.findById(idAtendimento);
			return new ResponseEntity<>(atendimento, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
		
	@DeleteMapping("/delete/{idAtendimento}")
	public ResponseEntity<String> delete(@PathVariable long idAtendimento){
		try {
			String mensagem = this.atendimentoService.delete(idAtendimento);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Deu esse erro aqui: "+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findByMonthAndYear")
	public ResponseEntity<List<Atendimento>> findByMonthAndYear(@RequestParam int ano, @RequestParam int mes) {
	    try {
	        List<Atendimento> atendimentos = atendimentoService.findByMonthAndYear(ano, mes);
	        return new ResponseEntity<>(atendimentos, HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	    }
	}
	
}
