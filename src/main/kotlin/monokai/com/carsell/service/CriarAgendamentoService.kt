package monokai.com.carsell.service

import monokai.com.carsell.domain.model.Agendamento
import monokai.com.carsell.repositories.AgendamentoRepository
import org.springframework.stereotype.Service

@Service
class CriarAgendamentoService(
    private val agendamentoRepository: AgendamentoRepository,
) {

    fun exec(agendamento: Agendamento): Agendamento {
        val carroAgendadoList = agendamentoRepository.findAgendamentoByCarro_Id(agendamento.carro.id!!)
        carroAgendadoList.map {agendamento1 -> agendamento1.dataHora}.forEach{ data ->
            if(data == agendamento.dataHora) throw RuntimeException("O carro já está agendado para este horário. Verifique outro horário.")
        }
        return agendamentoRepository.save(agendamento)
    }
}