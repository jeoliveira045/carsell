package monokai.com.carsell.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import software.amazon.awssdk.services.ses.SesClient
import software.amazon.awssdk.services.ses.model.Body
import software.amazon.awssdk.services.ses.model.Content
import software.amazon.awssdk.services.ses.model.Destination
import software.amazon.awssdk.services.ses.model.Message
import software.amazon.awssdk.services.ses.model.SendEmailRequest
import software.amazon.awssdk.services.ses.model.SesException

@Service
class EnviarEmailService(val sesClient: SesClient) {


        fun sendEmail(body: String) {
            val from = "jifood12@gmail.com"
            val to = "jean.mateus.1997@gmail.com"
            val subject = "Agendamento de test-drive"

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