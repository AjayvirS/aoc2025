package datastructures

import java.nio.file.Files
import java.nio.file.Paths

object Input {
    fun lines(name: String): List<String> {
        val path = Paths.get("resources", name)
        return Files.readAllLines(path);
    }

    fun text(name: String): String {
        val path = Paths.get("resources", name)
        return Files.readString(path)
    }

    fun ints(name: String): List<Int> {
        return lines(name).map { it.toInt() }
    }

    inline fun <reified T: Any> twoDArray(name: String, separator: String): Array<Array<T>>{
        val path = Paths.get("resources", name)
        return Files.readAllLines(path).map { line -> line.split(separator).map {
            element -> when(T::class) {
                Int::class -> element.trim().toInt() as T
                Double::class -> element.trim().toDouble() as T
                String::class -> element as T
                else -> error("Unsupported type: ${T::class.simpleName}")
            }
         }.toTypedArray()
        }.toTypedArray()
    }
}