package sistemaGestao.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    // Horários padrão para agendamento (pode ser ajustado conforme necessidade)
    private static final List<LocalTime> HORARIOS_DISPONIVEIS = List.of(
            LocalTime.of(9, 0),
            LocalTime.of(10, 0),
            LocalTime.of(11, 0),
            LocalTime.of(13, 0),
            LocalTime.of(14, 0),
            LocalTime.of(15, 0),
            LocalTime.of(16, 0),
            LocalTime.of(17, 0)
    );
    
    public long countByFuncionarioAndMonth(Long funcionarioId, int ano, int mes) {
        return atendimentoRepository.countByFuncionarioAndMonth(funcionarioId, ano, mes);
    }

    public long countByClienteAndMonth(Long clienteId, int ano, int mes) {
        return atendimentoRepository.countByClienteAndMonth(clienteId, ano, mes);
    }

    public List<LocalTime> getHorariosDisponiveis(Long funcionarioId, LocalDate dataAtendimento) {
        // Busca atendimentos do funcionário para a data
        List<Atendimento> atendimentos = atendimentoRepository.buscarAtendimentosPorFuncionarioEData(funcionarioId, dataAtendimento);

        // Obtém os horários ocupados
        List<LocalTime> horariosOcupados = atendimentos.stream()
                .map(Atendimento::getHora_atendimento)
                .toList();

        // Retorna apenas os horários disponíveis
        return HORARIOS_DISPONIVEIS.stream()
                .filter(horario -> !horariosOcupados.contains(horario))
                .toList();
    }
    
    public List<LocalTime> getHorariosIndisponiveis(Long funcionarioId, LocalDate dataAtendimento) {
        // Busca atendimentos do funcionário para a data
        List<Atendimento> atendimentos = atendimentoRepository.buscarAtendimentosPorFuncionarioEData(funcionarioId, dataAtendimento);

        // Obtém e retorna os horários ocupados
        return atendimentos.stream()
                .map(Atendimento::getHora_atendimento)
                .toList();
    }


    public String save(Atendimento atendimento) {
        try {
        	
        	System.out.println("Salvando atendimento com data: " + atendimento.getData_atendimento()); // Log de depuração.

        	
            // Verifica se o horário já está ocupado
            boolean horarioOcupado = atendimentoRepository.verificarHorarioOcupado(
                    atendimento.getFuncionario().getId(),
                    atendimento.getData_atendimento(),
                    atendimento.getHora_atendimento());

            if (horarioOcupado) {
                return "Horário já ocupado para o funcionário.";
            }

            // Verifica as chaves estrangeiras antes de salvar
            if (!funcionarioRepository.existsById(atendimento.getFuncionario().getId())) {
                return "Funcionário não encontrado!";
            }
            if (!clienteRepository.existsById(atendimento.getCliente().getId())) {
                return "Cliente não encontrado!";
            }
            if (!tipoAtendimentoRepository.existsById(atendimento.getTipo_atendimento().getId())) {
                return "Tipo de Atendimento não encontrado!";
            }

            // Salva o atendimento
            atendimentoRepository.save(atendimento);
            return "Atendimento salvo com sucesso!";
        } catch (Exception e) {
            return "Erro ao salvar o atendimento: " + e.getMessage();
        }
    }

    public List<Atendimento> findByMonthAndYear(int ano, int mes) {
        // Define o primeiro dia do mês
        LocalDate inicioMes = LocalDate.of(ano, mes, 1);

        // Define o último dia do mês
        LocalDate fimMes = inicioMes.withDayOfMonth(inicioMes.lengthOfMonth());

        // Busca os atendimentos no intervalo de datas
        return atendimentoRepository.buscarAtendimentosPorIntervalo(inicioMes, fimMes);
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
