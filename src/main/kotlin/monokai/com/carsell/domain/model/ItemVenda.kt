package monokai.com.carsell.domain.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull

@Entity
@Table(name = "itens_venda")
data class ItemVenda(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @OneToOne(optional = false)
    @JoinColumn(name = "carro_id", referencedColumnName = "id")
    @NotNull(message = "Precisa existir um carro na base para ser vendidd")
    val carro: Carro,

    val precoFinal: Double
)