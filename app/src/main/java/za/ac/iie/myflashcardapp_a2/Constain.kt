package za.ac.iie.myflashcardapp_a2

object Constants {

    const val TOTAL_QUESTION: String = "total_question"
    const val CORRECT_ANSWERS: String = "correct_answer"

    fun getQuestion(): ArrayList<Question> {
        val questionList = ArrayList<Question>()

        val que1 = Question(
            1,
            " Apartheid officially ended in 1990.",
            "True",
            "False",
            2
        )
        questionList.add(que1)

        //2
        val que2 = Question(
            2,
            "Egypt, Jordan, and Syria have all invaded Israel.",
            "True",
            "False",
            1
        )
        questionList.add(que2)

        //3
        val que3 = Question(
            3,
            "Franklin D. Roosevelt was the 32nd President of the United States.",
            "True",
            "False",
            1
        )
        questionList.add(que3)

        //4
        val que4 = Question(
            4,
            "The Soweto Uprising was sparked by the enforcement of English in black schools.",
            "True",
            "False",
            2
        )
        questionList.add(que4)

        val que5 = Question(
            5,
            "Back in 1999 Joshua Zulu got a A for his project",
            "True",
            "False",
            1
        )
        questionList.add(que5)



        return questionList
    }
}




