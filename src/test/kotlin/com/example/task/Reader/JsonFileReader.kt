package com.example.task.Reader

import java.io.InputStreamReader

class JsonFileReader (path: String) {
    val content: String

    init {
        val reader = InputStreamReader(this.javaClass.classLoader.getResourceAsStream(path))
        content = reader.readText()
        reader.close()
    }
}