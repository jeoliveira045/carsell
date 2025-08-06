package monokai.com.carsell.service

import monokai.com.carsell.domain.model.Agendamento
import monokai.com.carsell.repositories.AgendamentoRepository
import org.springframework.stereotype.Service
import java.time.LocalTime

@Service
class CriarAgendamentoService(
    private val agendamentoRepository: AgendamentoRepository,
) {

    fun exec(agendamento: Agendamento): Agendamento {
        val carroAgendadoList = agendamentoRepository.findAgendamentoByCarro_Id(agendamento.carro.id!!)
        carroAgendadoList.map {agendamento1 -> agendamento1.dataHora}.forEach{ data -> if(data == agendamento.dataHora) throw RuntimeException("O carro já está agendado para este horário. Verifique outro horário.") }
        val agendamentoCliente = carroAgendadoList.find { agendamento2 -> agendamento2.dataHora == agendamento.dataHora }!!
        if(agendamento.cliente.id == agendamentoCliente.id) throw RuntimeException("O cliente já realizou agendamento nesse horario!")
        val time = agendamento.dataHora.toLocalTime()
        val foraExpediente = time.isAfter(LocalTime.of(18,0)) || time.isBefore(LocalTime.of(8,0))
        if(foraExpediente)throw RuntimeException("O estabelecimento está fechado no momento. Escolha outro horario")

        return agendamentoRepository.save(agendamento)
    }
}