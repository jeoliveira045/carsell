package monokai.com.carsell.repositories

import monokai.com.carsell.domain.model.Carro
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CarroRepository : JpaRepository<Carro, Long>