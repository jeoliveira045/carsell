package monokai.com.carsell.domain.model.vw

import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.hibernate.annotations.Subselect

@Entity
@Subselect("""
    SELECT
            ROW_NUMBER() OVER (ORDER BY nome) AS id,
            nome,
            SUM((SELECT SUM(itens_venda.preco_final) AS preco_final
                 FROM itens_venda
                          INNER JOIN vendas v2 ON itens_venda.venda_id = v2.id
                 WHERE v1.id = v2.id
                 GROUP BY v2.id)) AS preco_final,
            EXTRACT(YEAR FROM v1.data_criacao) AS ano
FROM vendas v1
         INNER JOIN clientes ON clientes.id = v1.cliente_id
GROUP BY ano, nome

""")
data class RelatorioCompradoesPorAnoView(
    @Id
    val id: Long,
    val nome: String,
    val precoFinal: Double,
    val ano: Int
)
