package monokai.com.carsell

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CarsellApplication

fun main(args: Array<String>) {
    runApplication<CarsellApplication>(*args)
}
