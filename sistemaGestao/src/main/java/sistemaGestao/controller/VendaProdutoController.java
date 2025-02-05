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

import sistemaGestao.entity.VendaProduto;
import sistemaGestao.service.VendaProdutoService;

@RestController
@RequestMapping("/venda")
@CrossOrigin("http://localhost:4200")
public class VendaProdutoController {

    @Autowired
    private VendaProdutoService vendaProdutoService;  


    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody VendaProduto vendaProduto) {
        try {
            String mensagem = this.vendaProdutoService.save(vendaProduto); 
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Deu erro!" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@RequestBody VendaProduto vendaProduto, @PathVariable int id){
    	try {
			String mensagem = this.vendaProdutoService.update(id, vendaProduto);
			return new ResponseEntity<String>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Algo deu errado!! " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }
    
    @GetMapping("/findAll")
    public ResponseEntity<List<VendaProduto>> findAlll(){
    	try {
			List<VendaProduto> lista = this.vendaProdutoService.findAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
    }
 // Endpoint para filtrar as vendas por mês e ano
    @GetMapping("/findByDate/{year}/{month}")
    public ResponseEntity<List<VendaProduto>> findByDate(@PathVariable int year, @PathVariable int month) {
        try {
            List<VendaProduto> lista = this.vendaProdutoService.findByMonthAndYear(year, month);
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/findById/{idVendaProduto}")
    public ResponseEntity<VendaProduto> findById(@PathVariable int idVendaProduto){
    	try {
    		VendaProduto vendaProduto = this.vendaProdutoService.findById(idVendaProduto);
        	return new ResponseEntity<>(vendaProduto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
    	

    }
    
    
    @DeleteMapping("/delete/{idVendaProduto}")
    public ResponseEntity<String> delete(@PathVariable long idVendaProduto){
    	try {
			String mensagem = this.vendaProdutoService.delete(idVendaProduto);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Deu esse erro aqui: "+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }
}
