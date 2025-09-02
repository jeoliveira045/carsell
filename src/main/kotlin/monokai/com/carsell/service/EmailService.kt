package monokai.com.carsell.service

import jakarta.mail.Flags
import jakarta.mail.Folder
import jakarta.mail.Session
import org.springframework.stereotype.Component
import jakarta.mail.internet.MimeMessage
import jakarta.mail.search.FlagTerm
import java.util.Properties

@Component
class EmailService {

    fun buscarMensagensNaoLidas(): List<MimeMessage>{
        val props = Properties()
        props["mail.store.protocol"] = "imaps"

        val session = Session.getDefaultInstance(props, null)
        val store = session.getStore("imaps")
        store.connect("imap.gmail.com", "jifood12@gmail.com", "sejafeliz123")

        val inbox = store.getFolder("INBOX")
        inbox.open(Folder.READ_ONLY)

        val mensagens = inbox.search(FlagTerm(Flags(Flags.Flag.SEEN), false))
        return mensagens.map { it as MimeMessage }
    }

    fun extrairCpf(msg: MimeMessage): String{
        val corpo = msg.content.toString()

        val regex = Regex("""\d{3}\.\d{3}\.\d{3}-\d{2}""")
        return regex.find(corpo)?.value ?: ""
    }
}
