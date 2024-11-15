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


import sistemaGestao.entity.TipoAtendimento;
import sistemaGestao.service.TipoAtendimentoService;

@RestController
@RequestMapping("/tipoatendimento")
@CrossOrigin("http://localhost:4200")
public class TipoAtendimentoController {

	@Autowired
	private TipoAtendimentoService tipoAtendimentoService;
	
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody TipoAtendimento tipoAtendimento) {
        try {
            String mensagem = this.tipoAtendimentoService.save(tipoAtendimento); 
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Deu erro!" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
	
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@RequestBody TipoAtendimento tipoAtendimento, @PathVariable int id){
    	try {
			String mensagem = this.tipoAtendimentoService.update(id, tipoAtendimento);
			return new ResponseEntity<String>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Algo deu errado!! " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }
    
    @GetMapping("/findAll")
    public ResponseEntity<List<TipoAtendimento>> findAll(){
    	try {
			List<TipoAtendimento> lista = this.tipoAtendimentoService.findAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
    }
    
    @GetMapping("/findById/{idTipoAtendimento}")
    public ResponseEntity<TipoAtendimento> findById(@PathVariable int idTipoAtendimento){
    	try {
    		TipoAtendimento tipoAtendimento = this.tipoAtendimentoService.findById(idTipoAtendimento);
        	return new ResponseEntity<>(tipoAtendimento, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
    }
    
    @DeleteMapping("/delete/{idTipoAtendimento}")
    public ResponseEntity<String> delete(@PathVariable long idTipoAtendimento){
    	try {
			String mensagem = this.tipoAtendimentoService.delete(idTipoAtendimento);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Deu esse erro aqui: "+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }
	
}
