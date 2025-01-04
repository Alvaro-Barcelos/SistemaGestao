package sistemaGestao.controller;

import java.time.LocalDate;
import java.time.LocalTime;
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

    @GetMapping("/countByEntityAndMonth")
    public ResponseEntity<Long> countByEntityAndMonth(
            @RequestParam("tipo") String tipo, 
            @RequestParam("id") Long id, 
            @RequestParam("ano") int ano, 
            @RequestParam("mes") int mes) {
        try {
            long count;
            if (tipo.equalsIgnoreCase("funcionario")) {
                count = atendimentoService.countByFuncionarioAndMonth(id, ano, mes);
            } else if (tipo.equalsIgnoreCase("cliente")) {
                count = atendimentoService.countByClienteAndMonth(id, ano, mes);
            } else {
                return new ResponseEntity<>(0L, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(0L, HttpStatus.BAD_REQUEST);
        }
    }

    
    // Endpoint para buscar horários disponíveis
    @GetMapping("/horarios-disponiveis")
    public ResponseEntity<List<LocalTime>> getHorariosDisponiveis(
            @RequestParam Long Id,
            @RequestParam String data_atendimento) {
        try {
            LocalDate data = LocalDate.parse(data_atendimento);
            List<LocalTime> horariosDisponiveis = atendimentoService.getHorariosDisponiveis(Id, data);
            return new ResponseEntity<>(horariosDisponiveis, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    
    
    
 // Endpoint para buscar horários indisponíveis
    @GetMapping("/horarios-indisponiveis")
    public ResponseEntity<List<LocalTime>> getHorariosIndisponiveis(
            @RequestParam Long Id,
            @RequestParam String data_atendimento) {
        try {
            LocalDate data = LocalDate.parse(data_atendimento);
            List<LocalTime> horariosIndisponiveis = atendimentoService.getHorariosIndisponiveis(Id, data);
            return new ResponseEntity<>(horariosIndisponiveis, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Atendimento atendimento) {
        try {
            System.out.println("Data recebida: " + atendimento.getData_atendimento()); // Log para verificar a data recebida.
            String mensagem = this.atendimentoService.save(atendimento);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao salvar atendimento: " + e.getMessage(), HttpStatus.BAD_REQUEST);
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
