package monokai.com.carsell.domain.model

import jakarta.persistence.*

@Entity
@Table(name = "CARRO")
data class Carro(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val marca: String,
    val modelo: String,
    val ano: Int,
    val cor: String,

    @Column(name = "tipo_combustivel")
    val tipoCombustivel: String,

    val quilometragem: Int,
    val preco: Double,
    val vendido: Boolean
)