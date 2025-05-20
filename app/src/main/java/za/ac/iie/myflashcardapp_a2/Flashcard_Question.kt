package za.ac.iie.myflashcardapp_a2

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class Flashcard_Question : AppCompatActivity(), View.OnClickListener {

    var mCurrentPosition: Int = 1
    var mQuestionList: ArrayList<Question>? = null
    var mSelectedOptionPosition: Int = 0
    var mCorrectAnswers: Int = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_flashcard_question)

        val option_one = findViewById<TextView>(R.id.option_one)
        val option_two = findViewById<TextView>(R.id.option_two)
        val nextBtn = findViewById<Button>(R.id.nextBtn)

        mCurrentPosition = 1
        mQuestionList = Constants.getQuestion()

        setQuestion()

        option_one.setOnClickListener(this)
        option_two.setOnClickListener(this)
        nextBtn.setOnClickListener(this)
    }

    fun setQuestion() {
        val progressbar = findViewById<ProgressBar>(R.id.progressbar)
        val progress = findViewById<TextView>(R.id.progress1)
        val questions = findViewById<TextView>(R.id.questions1)
        val option_one = findViewById<TextView>(R.id.option_one)
        val option_two = findViewById<TextView>(R.id.option_two)
        val nextBtn = findViewById<Button>(R.id.nextBtn)

        val question = mQuestionList!![mCurrentPosition - 1]

        defaultOptionsView()

        if (mCurrentPosition == mQuestionList!!.size) {
            nextBtn.text = "DONE"
        } else {
            nextBtn.text = "NEXT"
        }

        progressbar.progress = mCurrentPosition
        progress.text = "$mCurrentPosition/${progressbar.max}"

        questions.text = question.question
        option_one.text = question.optionOne
        option_two.text = question.optionTwo
    }

    fun defaultOptionsView() {
        val option_one = findViewById<TextView>(R.id.option_one)
        val option_two = findViewById<TextView>(R.id.option_two)

        val options = ArrayList<TextView>()
        options.add(0, option_one)
        options.add(1, option_two)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    fun answerView(answer: Int, drawableView: Int) {
        val option_one = findViewById<TextView>(R.id.option_one)
        val option_two = findViewById<TextView>(R.id.option_two)

        when (answer) {
            1 -> option_one.background = ContextCompat.getDrawable(this, drawableView)
            2 -> option_two.background = ContextCompat.getDrawable(this, drawableView)
        }
    }

    fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.option_border_bg
        )
    }

    override fun onClick(v: View?) {
        val option_one = findViewById<TextView>(R.id.option_one)
        val option_two = findViewById<TextView>(R.id.option_two)
        val nextBtn = findViewById<Button>(R.id.nextBtn)

        when (v?.id) {
            R.id.option_one -> selectedOptionView(option_one, 1)
            R.id.option_two -> selectedOptionView(option_two, 2)
            R.id.nextBtn -> {
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++

                    if (mCurrentPosition <= mQuestionList!!.size) {
                        setQuestion()
                    } else {
                        val intent = Intent(this, ScoreScreen::class.java)
                        intent.putExtra("CORRECT_ANSWERS", mCorrectAnswers)
                        intent.putExtra("TOTAL_QUESTIONS", mQuestionList!!.size)
                        startActivity(intent)
                        finish()
                    }
                } else {
                    val question = mQuestionList!![mCurrentPosition - 1]
                    if (question.correctAns != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }else{
                        mCorrectAnswers++
                    }
                    answerView(question.correctAns, R.drawable.right_option_border_bg)

                    if (mCurrentPosition == mQuestionList!!.size) {
                        nextBtn.text = "DONE"
                    } else {
                        nextBtn.text = "NEXT QUESTION"
                    }

                    mSelectedOptionPosition = 0
                }
            }
        }
    }
}













