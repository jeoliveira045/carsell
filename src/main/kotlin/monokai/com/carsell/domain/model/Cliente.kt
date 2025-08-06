package monokai.com.carsell.domain.model

import jakarta.persistence.*

@Entity
@Table(name = "clientes")
data class Cliente(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val nome: String,
    val email: String,
    val telefone: String,
    val cpf: String,
    val saldo: Double,
)