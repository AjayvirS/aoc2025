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
}