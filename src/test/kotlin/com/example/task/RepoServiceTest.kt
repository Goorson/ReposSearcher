package com.example.task

import com.example.task.Reader.JsonFileReader
import com.example.task.controller.exceptions.UserNotFoundException
import com.example.task.model.AllReposReturnBody
import com.example.task.service.RepoService
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@AutoConfigureMockMvc
class RepoServiceTest (
    @Autowired val repoService: RepoService
        ){

    @Test
    fun `repo service returns correct value`(){
        val json = JsonFileReader("expectedJson.json")
        val decodedObjects: List<AllReposReturnBody> = Json.decodeFromString(json.content)
        runBlocking {
            Assertions.assertEquals(
                decodedObjects,
                repoService.getAllRepos("Goorson")
            )
        }
    }
    @Test
    fun `repo service incorrect user`(){
            Assertions.assertThrows(
                UserNotFoundException::class.java) { runBlocking {
                repoService.getAllRepos("Goorson123123")
            }
        }
    }
}