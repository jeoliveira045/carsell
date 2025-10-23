package monokai.com.carsell.rest.vw

import monokai.com.carsell.repositories.vw.RelatorioDataMesViewRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/relatorio-data-mes")
class RelatorioDataMesViewRest(private val repository: RelatorioDataMesViewRepository) {

    @GetMapping
    fun listarTodos() = repository.findAll()
}
