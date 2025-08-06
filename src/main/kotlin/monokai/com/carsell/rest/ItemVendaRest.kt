package monokai.com.carsell.rest

import monokai.com.carsell.domain.model.ItemVenda
import monokai.com.carsell.repositories.ItemVendaRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/itens-venda")
class ItemVendaRest(private val repository: ItemVendaRepository
) {

    @GetMapping
    fun listarTodos() = repository.findAll()

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long) = repository.findById(id)

    @PostMapping
    fun criar(@RequestBody itemVenda: ItemVenda) = repository.save(itemVenda)

    @PutMapping("/{id}")
    fun atualizar(@PathVariable id: Long, @RequestBody itemVenda: ItemVenda): ItemVenda {
        return repository.save(itemVenda.copy(id = id))
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long) = repository.deleteById(id)
}