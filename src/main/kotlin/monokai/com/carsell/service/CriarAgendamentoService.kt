package monokai.com.carsell.service

import monokai.com.carsell.domain.model.Agendamento
import monokai.com.carsell.repositories.AgendamentoRepository
import monokai.com.carsell.repositories.CarroRepository
import org.springframework.stereotype.Service
import software.amazon.awssdk.services.ses.SesClient
import software.amazon.awssdk.services.ses.model.Body
import software.amazon.awssdk.services.ses.model.Content
import software.amazon.awssdk.services.ses.model.Destination
import software.amazon.awssdk.services.ses.model.Message
import software.amazon.awssdk.services.ses.model.SendEmailRequest
import software.amazon.awssdk.services.ses.model.SesException
import java.time.LocalDate
import java.time.LocalTime

@Service
class CriarAgendamentoService(
    private val agendamentoRepository: AgendamentoRepository,
    private val carroRepository: CarroRepository,
    private val sesClient: SesClient
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

    fun sendEmail(body: String){
        val from = "jifood12@gmail.com"
        val to = "jean.mateus.1997@gmail.com"
        val subject = "Assunto teste"

        val destination = Destination.builder()
            .toAddresses(to)
            .build()

        val subjectContent = Content.builder()
            .data(subject)
            .charset("UTF-8")
            .build()

        val bodyContent = Content.builder()
            .data(body)
            .charset("UTF-8")
            .build()

        val message = Message.builder()
            .subject(subjectContent)
            .body(Body.builder().text(bodyContent).build())
            .build()

        val request = SendEmailRequest.builder()
            .source(from) // Precisa estar verificado no SES
            .destination(destination)
            .message(message)
            .build()

        try {
            sesClient.sendEmail(request)
            println("E-mail enviado com sucesso para $to")
        } catch (ex: SesException) {
            println("Erro ao enviar e-mail: ${ex.awsErrorDetails().errorMessage()}")
            throw ex
        }

    }
}
