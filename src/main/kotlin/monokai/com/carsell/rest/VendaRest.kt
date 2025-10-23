package monokai.com.carsell.rest

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import monokai.com.carsell.domain.model.Venda
import monokai.com.carsell.repositories.VendaRepository
import monokai.com.carsell.service.SalvarVendaService
import org.springframework.web.bind.annotation.*
import java.io.File

@RestController
@RequestMapping("/vendas")
class VendaRest(
    private val repository: VendaRepository,
    private val salvarVendaService: SalvarVendaService,
    vendaService: SalvarVendaService
) {

    @GetMapping
    fun listarTodas() = repository.findAll()

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long) = repository.findById(id)

    @PostMapping
    fun criar(@RequestBody venda: Venda) = salvarVendaService.exec(venda)

    @PutMapping("/{id}")
    fun atualizar(@PathVariable id: Long, @RequestBody venda: Venda): Venda {
        return salvarVendaService.exec(venda.copy(id = id))
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long) = repository.deleteById(id)

    @GetMapping("/inserir-dados")
    fun inserirVendasMockadas(){
        var om = jacksonObjectMapper().registerModules(JavaTimeModule())
        var vendas = om.readValue<List<Venda>>(File("vendas.json"))
        vendas.forEach {
            salvarVendaService.exec(it)
        }
    }

    @DeleteMapping
    fun deleteAll(){
        repository.deleteAll()
    }
}
