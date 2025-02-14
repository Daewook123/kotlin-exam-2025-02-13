package com.ll

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File


val directory = File("src/main/resources/db/wiseSaying")

fun main() {

    init()
    while (true) {
        println("== 명언 앱 ==")
        print("명령 : ")
        val command = readlnOrNull() ?: ""

        when (command) {
            "등록" -> {
                addQuote()
            }

            "목록" -> {
                getAllQuote()
            }

            "수정" -> {
                updateQuote()
            }

            "삭제" -> {
                deleteQuote()
            }

            "빌드" -> {
                buildQuote()
            }

            "종료" -> {
                break;
            }
        }
    }
}

fun init() {
    // 경로 지정
    directory.mkdirs() // 디렉토리가 없으면 생성

    val lastIdFile = File(directory, "lastId.txt")
    val dataJsonFile = File(directory, "data.json")

    if (!lastIdFile.exists()) {
        lastIdFile.writeText("0")
        println("lastId.txt 파일이 생성되었습니다.")
    }

    if(!dataJsonFile.exists()){
        dataJsonFile.writeText("")
        println("data.json 파일이 생성되었습니다.")
    }
}


fun addQuote() {
    val lastIdFile = File(directory, "lastId.txt")
    val lastId = lastIdFile.readText().toInt()

    print("명언을 입력하세요 : ")
    val content = readlnOrNull() ?: ""

    print("저자를 입력하세요 : ")
    val author = readlnOrNull() ?: ""

    val jsonString = Json.encodeToString(Quote(lastId + 1, content, author))
    File(directory, "${lastId + 1}.json").writeText(jsonString)
    lastIdFile.writeText("${lastId + 1}")
    println("${lastId + 1}번 명언이 등록되었습니다.")
}

fun getAllQuote() {
    val lastIdFile = File(directory, "lastId.txt")
    val lastId = lastIdFile.readText().toInt()
    println("번호 / 작가 / 명언")
    println("--------------------")

    for (id in lastId.downTo(1)) {
        runCatching {
            val jsonString = File(directory, "$id.json").readText()
            Json.decodeFromString<Quote>(jsonString)
        }.onSuccess { quote ->
            println("${quote.id} / ${quote.author} / ${quote.content}")
        }
    }
}

fun updateQuote() {
    print("명언 번호를 입력하세요 : ")
    val num = readlnOrNull() ?: ""

    runCatching {
        val jsonString = File(directory, "$num.json").readText()
        Json.decodeFromString<Quote>(jsonString)
    }.onSuccess { quote ->
        // 성공시 처리
        println("명언(기존) : ${quote.content}")
        print("명언을 입력하세요 : ")
        val content = readlnOrNull() ?: ""

        println("저자(기존) : ${quote.author}")
        print("저자를 입력하세요 : ")
        val author = readlnOrNull() ?: ""

        quote.content = content
        quote.author = author

        val jsonString = Json.encodeToString(Quote(num.toInt(), content, author))
        File(directory, "${num}.json").writeText(jsonString)

        println("${num}번 명언이 수정되었습니다.")
    }.onFailure {
        println("${num}번 명언은 존재하지 않습니다.")
    }
}


fun deleteQuote() {
    print("명언 번호를 입력하세요 : ")
    val num = readlnOrNull() ?: ""

    runCatching {
        val file = File(directory, "$num.json")
        if (!file.exists()) throw Exception("파일이 존재하지 않습니다")
        file.delete()
    }.onSuccess {
        println("${num}번 명언이 삭제되었습니다.")
    }.onFailure {
        println("${num}번 명언은 존재하지 않습니다.")
    }
}

fun buildQuote() {
    val lastIdFile = File(directory, "lastId.txt")
    val lastId = lastIdFile.readText().toInt()
    val quotes = mutableListOf<String>()

    for (id in 1..lastId) {
        runCatching {
            val jsonString = File(directory, "$id.json").readText()
            quotes.add(jsonString)
        }.onFailure {

        }
    }

    val resultJson = "[${quotes.joinToString(",")}]"
    File(directory, "data.json").writeText(resultJson)
    println("data.json 파일의 내용이 갱신되었습니다.")
}