package monokai.com.carsell.service

import jakarta.persistence.EntityNotFoundException
import monokai.com.carsell.domain.model.Venda
import monokai.com.carsell.repositories.ClienteRepository
import monokai.com.carsell.repositories.VendaRepository
import org.springframework.stereotype.Service

@Service
class CriarVendaService(
    val vendaRepository: VendaRepository,
    val clienteRepository: ClienteRepository
)
{
    fun exec(venda: Venda): Venda {
        clienteRepository.findById(venda.cliente.id!!).orElseThrow { -> EntityNotFoundException("Cliente Não encotrado!") }
        val totalVenda = venda.itens.map { venda1 -> venda1.carro.preco }.reduce {acc, preco -> acc + preco}
        if(totalVenda > venda.cliente.saldo) throw RuntimeException("Saldo Insuficiente!")
        return vendaRepository.save(venda)
    }

}