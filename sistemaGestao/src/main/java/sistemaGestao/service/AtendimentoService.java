package sistemaGestao.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sistemaGestao.entity.Atendimento;
import sistemaGestao.entity.Cliente;
import sistemaGestao.entity.Funcionario;
import sistemaGestao.entity.TipoAtendimento;
import sistemaGestao.repository.AtendimentoRepository;
import sistemaGestao.repository.ClienteRepository;
import sistemaGestao.repository.FuncionarioRepository;
import sistemaGestao.repository.TipoAtendimentoRepository;

@Service
public class AtendimentoService {

    @Autowired
    private AtendimentoRepository atendimentoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private TipoAtendimentoRepository tipoAtendimentoRepository;
	
    public String save(Atendimento atendimento) {
        try {
            // Verificar e criar o Funcionario, Cliente, e TipoAtendimento caso não existam
            if (atendimento.getFuncionario() == null || atendimento.getFuncionario().getId() == 0) {
                return "Funcionário não pode ser nulo!";
            } else {
                // Verifica se o Funcionario existe no banco
                Funcionario funcionario = funcionarioRepository.findById(atendimento.getFuncionario().getId()).orElse(null);
                if (funcionario != null) {
                    atendimento.setFuncionario(funcionario);
                } else {
                    return "Funcionário não encontrado!";
                }
            }

            if (atendimento.getCliente() == null || atendimento.getCliente().getId() == 0) {
                return "Cliente não pode ser nulo!";
            } else {
                // Verifica se o Cliente existe no banco
                Cliente cliente = clienteRepository.findById(atendimento.getCliente().getId()).orElse(null);
                if (cliente != null) {
                    atendimento.setCliente(cliente);
                } else {
                    return "Cliente não encontrado!";
                }
            }

            if (atendimento.getTipo_atendimento() == null || atendimento.getTipo_atendimento().getId() == 0) {
                return "Tipo de Atendimento não pode ser nulo!";
            } else {
                // Verifica se o TipoAtendimento existe no banco
                TipoAtendimento tipoAtendimento = tipoAtendimentoRepository.findById(atendimento.getTipo_atendimento().getId()).orElse(null);
                if (tipoAtendimento != null) {
                    atendimento.setTipo_atendimento(tipoAtendimento);
                } else {
                    return "Tipo de Atendimento não encontrado!";
                }
            }

            // Salvar o atendimento com as chaves estrangeiras corretamente associadas
            Atendimento savedAtendimento = atendimentoRepository.save(atendimento);

            return "Atendimento " + savedAtendimento.getId() + " salvo com sucesso!";
        } catch (Exception e) {
            return "Erro ao salvar o atendimento: " + e.getMessage();
        }
    }

    public List<Atendimento> findByMonthAndYear(int ano, int mes) {
        // Define o primeiro dia do mês
        LocalDate inicioMes = LocalDate.of(ano, mes, 1);

        // Define o último dia do mês
        LocalDate fimMes = inicioMes.withDayOfMonth(inicioMes.lengthOfMonth());

        // Agora, retornamos os atendimentos dentro desse intervalo de LocalDate
        return atendimentoRepository.findByDataAtendimentoBetween(inicioMes, fimMes);
    }

	
	public String update(int id, Atendimento atendimento) {
		atendimento.setId(id);
		this.atendimentoRepository.save(atendimento);
		return atendimento.getId()+ " atualizado com sucesso!";
	}
	

	public List<Atendimento> findAll(){
		return this.atendimentoRepository.findAll();
	}
	
	public Atendimento findById(long idAtendimento){
		Atendimento atendimento = this.atendimentoRepository.findById(idAtendimento).get();
		return atendimento;
	}
	
	public String delete(long idAtendimento) {
		this.atendimentoRepository.deleteById(idAtendimento);
		return "Atendimento deletado com sucesso!";
	}
}
