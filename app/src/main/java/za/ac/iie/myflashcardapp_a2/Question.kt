package za.ac.iie.myflashcardapp_a2

import java.io.Serializable

data class Question (
    val id: Int,
    val question: String,
    val optionOne: String,
    val optionTwo: String,
    val correctAns: Int,
) : Serializable