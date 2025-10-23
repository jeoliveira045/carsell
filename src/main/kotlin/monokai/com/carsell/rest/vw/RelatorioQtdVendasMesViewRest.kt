package monokai.com.carsell.rest.vw

import monokai.com.carsell.repositories.vw.RelatorioQtdVendasMesViewRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/relatorio-qtd-vendas-mes")
class RelatorioQtdVendasMesViewRest(private val repository: RelatorioQtdVendasMesViewRepository) {

    @GetMapping
    fun listarTodos() = repository.findAll()
}
