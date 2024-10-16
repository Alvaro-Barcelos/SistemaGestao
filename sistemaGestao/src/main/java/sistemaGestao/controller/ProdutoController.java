package sistemaGestao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sistemaGestao.entity.Produto;
import sistemaGestao.service.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;  


    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Produto produto) {
        try {
            String mensagem = this.produtoService.save(produto); 
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Deu erro!" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@RequestBody Produto produto, @PathVariable int id){
    	try {
			String mensagem = this.produtoService.update(id, produto);
			return new ResponseEntity<String>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Algo deu errado!! " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }
    
    @GetMapping("/findAll")
    public ResponseEntity<List<Produto>> findAlll(){
    	try {
			List<Produto> lista = this.produtoService.findAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
    }
    
    @GetMapping("/findById/{idProduto}")
    public ResponseEntity<Produto> findById(@PathVariable int idProduto){
    	try {
        	Produto produto = this.produtoService.findById(idProduto);
        	return new ResponseEntity<>(produto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
    	

    }
    
    
    @DeleteMapping("/delete/{idProduto}")
    public ResponseEntity<String> delete(@PathVariable long idProduto){
    	try {
			String mensagem = this.produtoService.delete(idProduto);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Deu esse erro aqui: "+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }
    
}