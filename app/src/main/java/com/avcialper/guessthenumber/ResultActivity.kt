package com.avcialper.guessthenumber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.avcialper.guessthenumber.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val isCorrect = intent.getBooleanExtra("isCorrect", false)
        val randomNumber = intent.getIntExtra("randomNumber", 0)

        binding.apply {

            if(isCorrect) {

                resultGif.setImageResource(R.drawable.celebration)
                randomNumberText.text = ""
                showResult.setText(R.string.success)

            } else {

                resultGif.setImageResource(R.drawable.sad)
                randomNumberText.text = randomNumber.toString()
                showResult.setText(R.string.lose)

            }

            button.setOnClickListener {

                val intent = Intent(this@ResultActivity, GuessActivity::class.java)
                finish()
                startActivity(intent)

            }

        }
    }
}