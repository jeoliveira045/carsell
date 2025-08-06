package monokai.com.carsell.rest

import monokai.com.carsell.domain.model.Carro
import monokai.com.carsell.repositories.CarroRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/carros")
class CarroRest(private val repository: CarroRepository) {

    @GetMapping
    fun listarTodos() = repository.findAll()

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long) = repository.findById(id)

    @PostMapping
    fun criar(@RequestBody carro: Carro) = repository.save(carro)

    @PutMapping("/{id}")
    fun atualizar(@PathVariable id: Long, @RequestBody carro: Carro): Carro {
        return repository.save(carro.copy(id = id))
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long) = repository.deleteById(id)

    @GetMapping("/inserir-dados")
    fun inserirDadosMockados(): List<Carro> {
        val carros = listOf(
            Carro(
                id = null,
                marca = "Toyota",
                modelo = "Corolla",
                ano = 2020,
                cor = "Prata",
                tipoCombustivel = "Flex",
                quilometragem = 45000,
                preco = 89000.0,
                vendido = false
            ),
            Carro(
                id = null,
                marca = "Honda",
                modelo = "Civic",
                ano = 2019,
                cor = "Preto",
                tipoCombustivel = "Gasolina",
                quilometragem = 60000,
                preco = 83000.0,
                vendido = false
            ),
            Carro(
                id = null,
                marca = "Volkswagen",
                modelo = "Golf",
                ano = 2018,
                cor = "Branco",
                tipoCombustivel = "Flex",
                quilometragem = 70000,
                preco = 75000.0,
                vendido = false
            )
        )
        return repository.saveAll(carros)
    }
}