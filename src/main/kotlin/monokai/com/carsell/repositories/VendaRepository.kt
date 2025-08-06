package monokai.com.carsell.repositories

import monokai.com.carsell.domain.model.Venda
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VendaRepository : JpaRepository<Venda, Long>