package com.example.mvvm_unittesting

import java.io.BufferedReader
import java.io.InputStreamReader

object Helper {

    fun readFileResource(fileName: String): String {

        val inputStream = Helper::class.java.getResourceAsStream(fileName)
        val reader = InputStreamReader(inputStream,"UTF-8")
        val content = StringBuilder()
        reader.readLines().forEach() { content.append(it) }
        return content.toString()

    }
}