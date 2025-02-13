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
            "목록" ->{
                println("번호 / 작가 / 명언")
                println("--------------------")
                for(quote in quoteList.reversed()){
                    println("${quoteList.indexOf(quote)+1} / ${quote.author} / ${quote.text}")
                }
            }
            "종료" ->{
                break;
            }
        }
    }
}