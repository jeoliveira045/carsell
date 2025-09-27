package monokai.com.carsell.rest

import monokai.com.carsell.domain.model.Venda
import monokai.com.carsell.repositories.VendaRepository
import monokai.com.carsell.service.SalvarVendaService
import org.springframework.web.bind.annotation.*

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
}