package sistemaGestao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sistemaGestao.entity.TipoAtendimento;
import sistemaGestao.repository.TipoAtendimentoRepository;

@Service
public class TipoAtendimentoService {
	
	@Autowired
	private TipoAtendimentoRepository tipoAtendimentoRepository;
	
	public String save(TipoAtendimento tipoAtendimento) {
		this.tipoAtendimentoRepository.save(tipoAtendimento);
		return  "Cadastrado com sucesso!";
	}
	
	public String update(int id, TipoAtendimento tipoAtendimento) {
		tipoAtendimento.setId(id);
		this.tipoAtendimentoRepository.save(tipoAtendimento);
		return tipoAtendimento.getDescricao()+" atualizado com sucesso!";
	}
	
	public List<TipoAtendimento> findAll(){
		return this.tipoAtendimentoRepository.findAll();
	}
	
	public TipoAtendimento findById(long idTipoAtendimento) {
		TipoAtendimento tipoAtendimento = this.tipoAtendimentoRepository.findById(idTipoAtendimento).get();
		return tipoAtendimento;
	}
	
	public String delete(long idTipoAtendimento) {
		this.tipoAtendimentoRepository.deleteById(idTipoAtendimento);
		return "Atendimento deletado com sucesso!"; 
	}
	
}
