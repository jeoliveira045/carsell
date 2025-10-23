package monokai.com.carsell.domain.model.vw

import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.hibernate.annotations.Subselect

@Entity
@Subselect("""
    SELECT
    ROWNUM AS ID,
    CASE EXTRACT(MONTH FROM DATA_CRIACAO)
        WHEN 1 THEN 'Janeiro'
        WHEN 2 THEN 'Fevereiro'
        WHEN 3 THEN 'Março'
        WHEN 4 THEN 'Abril'
        WHEN 5 THEN 'Maio'
        WHEN 6 THEN 'Junho'
        WHEN 7 THEN 'Julho'
        WHEN 8 THEN 'Agosto'
        WHEN 9 THEN 'Setembro'
        WHEN 10 THEN 'Outubro'
        WHEN 11 THEN 'Novembro'
        WHEN 12 THEN 'Dezembro'
        END AS MES,
    COUNT(*) AS QTD_VENDAS
FROM VENDAS
GROUP BY EXTRACT(MONTH FROM DATA_CRIACAO)
ORDER BY EXTRACT(MONTH FROM DATA_CRIACAO) ASC;
""")
data class RelatorioDataMesView(
    @Id
    val id: Long,
    val mes: String,
    val qtdVendas: String
)
