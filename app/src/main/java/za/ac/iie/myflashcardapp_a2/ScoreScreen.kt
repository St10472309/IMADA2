package za.ac.iie.myflashcardapp_a2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ScoreScreen : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_score_screen)

        val reviewButton = findViewById<Button>(R.id.btnReview)
        val restartButton = findViewById<Button>(R.id.btnRestart)
        val exitButton = findViewById<Button>(R.id.btnExit)
        val resultiv = findViewById<ImageView>(R.id.resultiv)
        val scoreTextView = findViewById<TextView>(R.id.resultscr)
        val feedbackTextView = findViewById<TextView>(R.id.txt3)


        // Get score and total questions
        val totalQuestions = intent.getIntExtra("TOTAL_QUESTIONS", 0)
        val score = intent.getIntExtra("CORRECT_ANSWERS", 0)

        // Set texts
        scoreTextView.text = "Your score: $score/$totalQuestions"
        feedbackTextView.text = if (score >= 3) "Great job!" else "Keep practicing!"

        if (score >= 3) {
            resultiv.setImageResource(R.drawable.thumbs)
        } else {
            resultiv.setImageResource(R.drawable.down)
        }
        // Buttons
        restartButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        exitButton.setOnClickListener {
            finishAffinity()
        }
        reviewButton.setOnClickListener {
            val intent = Intent(this, ReviewPage::class.java)
            startActivity(intent)
        }
    }
}
