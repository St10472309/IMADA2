package za.ac.iie.myflashcardapp_a2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ReviewPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_review_page)

        // Display all questions and answers
                val reviewText = findViewById<TextView>(R.id.reviewText)
                val restartButton = findViewById<Button>(R.id.restartButton)
                var userResults = intent.getBooleanArrayExtra("userResults") ?: booleanArrayOf()

                // Get the questions and answers (could also pass via Intent)
                val questions = arrayOf(
                    " Apartheid officially ended in 1990.",
                    "Egypt, Jordan, and Syria have all invaded Israel.",
                    "Franklin D. Roosevelt was the 32nd President of the United States.",
                    "The Soweto Uprising was sparked by the enforcement of English in black schools.",
                    "Back in 1999 Joshua Zulu got a A for his project"

                )

                val answers = booleanArrayOf(false, true, true, false, true)

                // Build review content
                val reviewContent = buildString {
                    append("Review Questions:\n\n")
                    questions.forEachIndexed { index, question ->
                        append("${index + 1}. $question\n")
                        append("   Correct Answer: ${if (answers[index]) "True" else "False"}\n\n")
                    }
                }

                reviewText.text = reviewContent

                // Restart button click handler
                restartButton.setOnClickListener {
                    // Create intent to go back to MainActivity
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish() // Close current activity
                }
            }
        }
