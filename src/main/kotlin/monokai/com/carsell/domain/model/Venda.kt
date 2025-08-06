package monokai.com.carsell.domain.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "vendas")
data class Venda(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val totalVenda: Long,
    val formaPagamento: String,

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "venda_id")
    val itens: List<ItemVenda> = emptyList(),

    @ManyToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    @NotNull(message = "O cliente não pode ser nulo")
    val cliente: Cliente
)