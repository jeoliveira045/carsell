package monokai.com.carsell.repositories.vw

import monokai.com.carsell.domain.model.vw.RelatorioQtdVendasMesView
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RelatorioQtdVendasMesViewRepository : JpaRepository<RelatorioQtdVendasMesView, Long>{
}
