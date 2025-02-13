package com.ll

fun main() {
    var lastId = 1
    val quoteList = mutableListOf<Quote>()
    while(true){
        println("== 명언 앱 ==")
        print("명령 : ")
        val command = readlnOrNull() ?: ""

        when (command) {
            "등록" -> {
                print("명언을 입력하세요 : ")
                val text = readlnOrNull() ?: ""

                print("저자를 입력하세요 : ")
                val author = readlnOrNull() ?: ""

                quoteList.add(Quote(lastId, text, author))
                println("${lastId}번 명언이 등록되었습니다.")
                lastId++
            }
            "목록" -> {
                println("번호 / 작가 / 명언")
                println("--------------------")
                for(quote in quoteList.reversed()){
                    println("${quote.id} / ${quote.author} / ${quote.text}")
                }
            }
            "수정" ->{
                print("명언 번호를 입력하세요 : ")
                val num = readlnOrNull() ?: ""
                try {
                    val targetQuote = quoteList.find { it.id == num.toInt() }
                    if (targetQuote == null) {
                        println("${num}번 명언은 존재하지 않습니다.")
                    } else {
                        println("명언(기존) : ${targetQuote.text}")
                        print("명언을 입력하세요 : ")
                        val text = readlnOrNull() ?: ""

                        println("저자(기존) : ${targetQuote.author}")
                        print("저자를 입력하세요 : ")
                        val author = readlnOrNull() ?: ""

                        targetQuote.text = text
                        targetQuote.author = author
                    }
                } catch (e: Exception) {
                    println("${num}번 명언은 존재하지 않습니다.")
                }
            }
            "삭제" -> {
                print("명언 번호를 입력하세요 : ")
                val num = readlnOrNull() ?: ""
                try {
                    val targetQuote = quoteList.find { it.id == num.toInt() }
                    if (targetQuote == null) {
                        println("${num}번 명언은 존재하지 않습니다.")
                    } else {
                        quoteList.remove(targetQuote)
                        println("${num}번 명언이 삭제되었습니다.")
                    }
                } catch (e: Exception) {
                    println("${num}번 명언은 존재하지 않습니다.")
                }
            }
            "종료" ->{
                break;
            }
        }
    }
}