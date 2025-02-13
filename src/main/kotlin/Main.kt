package com.ll

fun main() {
    while(true){
        println("== 명언 앱 ==")
        println("명령")
        val command = readlnOrNull() ?: ""

        when (command) {
            "종료" ->{
                break;
            }
        }
//        // 사용자로부터 명언 입력 받기
//        print("명언을 입력하세요: ")
//
//
//        // 사용자로부터 저자 입력 받기
//        print("저자를 입력하세요: ")
//        val author = readLine() ?: ""
//
//        // Quote 클래스의 객체 생성
//        val quote = Quote(text, author)
//        println("명언: ${quote.text} - ${quote.author}")
    }
}