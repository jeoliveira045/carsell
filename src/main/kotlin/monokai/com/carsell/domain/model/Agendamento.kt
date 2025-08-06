package monokai.com.carsell.domain.model


import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Agendamento(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val dataHora: LocalDateTime,

    val observacoes: String? = null,

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    val cliente: Cliente,

    @ManyToOne
    @JoinColumn(name = "carro_id", nullable = false)
    val carro: Carro
)