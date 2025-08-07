package monokai.com.carsell.service

import monokai.com.carsell.domain.model.Agendamento
import monokai.com.carsell.repositories.AgendamentoRepository
import monokai.com.carsell.repositories.CarroRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalTime

@Service
class CriarAgendamentoService(
    private val agendamentoRepository: AgendamentoRepository,
    private val carroRepository: CarroRepository
) {

    fun exec(agendamento: Agendamento): Agendamento {
        val carro = carroRepository.findById(agendamento.carro.id!!)
        val carroAgendadoList = agendamentoRepository.findAgendamentoByCarro_Id(agendamento.carro.id!!)
        val clienteCarroAgendado = agendamentoRepository.findAgendamentoByCliente_Id(agendamento.cliente.id!!)
        carroAgendadoList.map {agendamento1 -> agendamento1.dataHora}.forEach{ data -> if(data == agendamento.dataHora) throw RuntimeException("O carro já está agendado para este horário. Verifique outro horário.") }
        val agendamentoCliente = clienteCarroAgendado.find { agendamento2 -> agendamento2.dataHora == agendamento.dataHora }
        if(agendamentoCliente != null) throw RuntimeException("O cliente já realizou agendamento nesse horario!")
        val time = agendamento.dataHora.toLocalTime()
        val date = agendamento.dataHora.toLocalDate()
        val foraExpediente = time.isAfter(LocalTime.of(18,0)) || time.isBefore(LocalTime.of(8,0))
        if(foraExpediente)throw RuntimeException("O estabelecimento está fechado no momento. Escolha outro horario")
        if(date.isBefore(LocalDate.now())) throw RuntimeException("O agendamento não pode ocorrer em uma data anterior a hoje.")
        if(time.hour - LocalTime.now().hour > 2) throw RuntimeException("O agendamento não pode ser em menos de 2 horas após sua marcação")





        return agendamentoRepository.save(agendamento)
    }
}