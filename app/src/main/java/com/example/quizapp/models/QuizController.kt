package com.example.quizapp.models

class QuizController {
    val questions = arrayListOf<Question>()

    init{
//        val lines = File("src/main/questions.txt").readLines()
//        for(i in 0..lines.size-1 step 5) {
//            val answers = arrayListOf<String>(lines[i + 1], lines[i + 2], lines[i + 3], lines[i + 4])
//            val q = Question(lines[i], answers)
//            questions.add(q);
//        }
    }

    fun doQuiz(numberOfQuestions: Int){
        randomizeQuestions()
        var correctAnswers = 0
        for(i in 0..numberOfQuestions-1) {
            println(questions[i].text)
            println(questions[i].answers.shuffled())
            print("Correct answer: ")
            val res = readLine()
            if(res == questions[i].answers[0]){
                correctAnswers ++
            }
        }
        println("" + correctAnswers + "/" + numberOfQuestions)
    }

    fun randomizeQuestions(){
        questions.toMutableList()
        questions.shuffle()
    }
}