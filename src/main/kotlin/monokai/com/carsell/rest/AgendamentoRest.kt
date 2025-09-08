package monokai.com.carsell.rest

import monokai.com.carsell.domain.model.Agendamento
import monokai.com.carsell.repositories.AgendamentoRepository
import monokai.com.carsell.service.CriarAgendamentoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/api/agendamentos")
class AgendamentoRest(
    private val agendamentoRepository: AgendamentoRepository,
    var criarAgendamentoService: CriarAgendamentoService
) {

    @GetMapping
    fun listarTodos(): List<Agendamento> =
        agendamentoRepository.findAll()

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<Agendamento> =
        agendamentoRepository.findById(id)
            .map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.notFound().build())

    @PostMapping
    fun criar(@RequestBody agendamento: Agendamento, uriBuilder: UriComponentsBuilder): ResponseEntity<Agendamento> {
        return ResponseEntity.ok(criarAgendamentoService.exec(agendamento))
    }

    @PutMapping("/{id}")
    fun atualizar(@PathVariable id: Long, @RequestBody agendamento: Agendamento): ResponseEntity<Agendamento> {
        return if (agendamentoRepository.existsById(id)) {
            val atualizado = agendamentoRepository.save(agendamento.copy(id = id))
            ResponseEntity.ok(atualizado)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long): ResponseEntity<Void> {
        return if (agendamentoRepository.existsById(id)) {
            agendamentoRepository.deleteById(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
//
//    @PostMapping("/enviar-email")
//    fun enviarEmail(@RequestBody message: Message): ResponseEntity<Void>{
//        criarAgendamentoService.sendEmail(message.messageBody)
//        return ResponseEntity.noContent().build()
//    }
}
