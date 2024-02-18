package com.example.task.controller.exceptions

class UserNotFoundException(message: String, val status: Int): RuntimeException(message) {
}
