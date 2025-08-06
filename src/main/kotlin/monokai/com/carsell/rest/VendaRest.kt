package monokai.com.carsell.rest

import monokai.com.carsell.domain.model.Venda
import monokai.com.carsell.repositories.VendaRepository
import monokai.com.carsell.service.CriarVendaService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/vendas")
class VendaRest(
    private val repository: VendaRepository,
    private val criarVendaService: CriarVendaService,
    vendaService: CriarVendaService
) {

    @GetMapping
    fun listarTodas() = repository.findAll()

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long) = repository.findById(id)

    @PostMapping
    fun criar(@RequestBody venda: Venda) = criarVendaService.exec(venda)

    @PutMapping("/{id}")
    fun atualizar(@PathVariable id: Long, @RequestBody venda: Venda): Venda {
        return repository.save(venda.copy(id = id))
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long) = repository.deleteById(id)
}