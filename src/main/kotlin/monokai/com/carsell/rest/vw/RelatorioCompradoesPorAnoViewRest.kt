package monokai.com.carsell.rest.vw

import monokai.com.carsell.domain.model.vw.RelatorioCompradoesPorAnoView
import monokai.com.carsell.repositories.vw.RelatorioCompradoesPorAnoViewRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/relatorio-compras-por-ano")
class RelatorioCompradoesPorAnoViewRest(private val repository: RelatorioCompradoesPorAnoViewRepository) {

    @GetMapping
    fun listarTodos(): MutableList<RelatorioCompradoesPorAnoView> = repository.findAll()
}