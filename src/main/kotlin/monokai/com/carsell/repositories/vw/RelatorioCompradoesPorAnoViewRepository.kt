package monokai.com.carsell.repositories.vw

import monokai.com.carsell.domain.model.vw.RelatorioCompradoesPorAnoView
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RelatorioCompradoesPorAnoViewRepository : JpaRepository<RelatorioCompradoesPorAnoView, Long>{
}