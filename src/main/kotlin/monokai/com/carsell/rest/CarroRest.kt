package monokai.com.carsell.rest

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectReader
import com.fasterxml.jackson.module.kotlin.readValue
import monokai.com.carsell.domain.model.Carro
import monokai.com.carsell.repositories.CarroRepository
import org.springframework.web.bind.annotation.*
import java.io.File
import java.io.InputStream

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
    fun inserirDadosMockados(inputStream: InputStream): List<Carro> {
        var carrosJson = File("carros.json")
        var om = ObjectMapper()

        val carros: List<Carro> = om.readValue(carrosJson.readText())

        return repository.saveAll(carros)
    }
}