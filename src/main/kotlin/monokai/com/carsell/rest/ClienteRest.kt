package monokai.com.carsell.rest

import monokai.com.carsell.domain.model.Cliente
import monokai.com.carsell.repositories.ClienteRepository
import org.hibernate.validator.internal.constraintvalidators.bv.time.futureorpresent.FutureOrPresentValidatorForOffsetTime
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/clientes")
class ClienteRest(
    private val repository: ClienteRepository,
) {

    @GetMapping
    fun listarTodos() = repository.findAll()

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long) = repository.findById(id)

    @PostMapping
    fun criar(@RequestBody cliente: Cliente) = repository.save(cliente)

    @PutMapping("/{id}")
    fun atualizar(@PathVariable id: Long, @RequestBody cliente: Cliente): Cliente {
        return repository.save(cliente.copy(id = id))
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long) = repository.deleteById(id)

    @GetMapping("inserir-dados")
    fun inserirDadosMockadosClientes(): List<Cliente> {
        val clientes = listOf(
            Cliente(
                id = null,
                nome = "Ana Paula",
                email = "ana.paula@example.com",
                telefone = "(11) 91234-5678",
                cpf = "123.456.789-00",
                saldo = 100000.0,
            ),
            Cliente(
                id = null,
                nome = "Carlos Eduardo",
                email = "carlos.eduardo@example.com",
                telefone = "(21) 99876-5432",
                cpf = "987.654.321-00",
                saldo = 120000.0,
            ),
            Cliente(
                id = null,
                nome = "Juliana Mendes",
                email = "juliana.mendes@example.com",
                telefone = "(31) 93456-7890",
                cpf = "456.789.123-00",
                saldo = 75000.0,
            )
        )
        return repository.saveAll(clientes)
    }

    @DeleteMapping("/limpar-base")
    fun clear(){
        repository.deleteAll()
    }
}