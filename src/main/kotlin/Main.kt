package com.ll

fun main() {

    val quoteList = mutableListOf<Quote>()
    while(true){
        println("== 명언 앱 ==")
        print("명령 : ")
        val command = readlnOrNull() ?: ""

        when (command) {
            "등록" ->{
                print("명언을 입력하세요 : ")
                val text = readlnOrNull() ?: ""

                print("저자를 입력하세요 : ")
                val author = readlnOrNull() ?: ""

                quoteList.add(Quote(text, author));
                println("${quoteList.size}번째 명언이 등록되었습니다.")
            }
            "종료" ->{
                break;
            }
        }
    }
}