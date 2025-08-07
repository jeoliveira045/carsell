package monokai.com.carsell.repositories

import monokai.com.carsell.domain.model.Agendamento
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AgendamentoRepository : JpaRepository<Agendamento, Long>{
    fun findAgendamentoByCarro_Id(id: Long): List<Agendamento>
    fun findAgendamentoByCliente_Id(id: Long): List<Agendamento>
}