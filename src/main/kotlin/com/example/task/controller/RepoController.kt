package com.example.task.controller

import com.example.task.controller.exceptions.UserNotFoundException
import com.example.task.service.RepoService
import io.ktor.client.plugins.*
import io.ktor.http.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/repos")
class RepoController (
    @Autowired val repoService: RepoService
    ){

    @GetMapping("/all/{username}", produces = ["application/json"])
    suspend fun getAllReposWithUsername(@PathVariable username: String): ResponseEntity<Any>{
        return try{
            val result = repoService.getAllRepos(username)
            ResponseEntity.ok(result)
        } catch (e: UserNotFoundException) {
            ResponseEntity(
                mapOf(
                    "status" to e.status,
                    "message" to e.message
                ),
                HttpStatus.NOT_FOUND
            )
        }
    }
}