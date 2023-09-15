package com.avcialper.guessthenumber

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.avcialper.guessthenumber.databinding.ActivityGuessBinding
import kotlin.random.Random

class GuessActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGuessBinding
    private var randomNumber = 0
    private var counter = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        randomNumber = Random.nextInt(101)

        binding.apply {

            guess.setOnClickListener {

                if (editTextNumber.text.isBlank())
                    error.visibility = View.VISIBLE
                else {

                    val input = editTextNumber.text.toString().toInt()
                    if (input > 100) {
                        error.visibility = View.VISIBLE
                        return@setOnClickListener
                    }
                    error.visibility = View.INVISIBLE

                    counter--
                    counterText.text = "Kalan Hak: $counter"

                    Log.e("TEST", "$randomNumber")

                    if (input == randomNumber) {
                        goToResultActivity(true)
                        return@setOnClickListener
                    } else if (input > randomNumber)
                        guideText.setText(R.string.decrease)
                    else
                        guideText.setText(R.string.increase)

                    if (counter == 0)
                        goToResultActivity(false)

                    editTextNumber.setText("")

                }

            }

        }

    }

    private fun goToResultActivity(isCorrect: Boolean) {

        val intent = Intent(this@GuessActivity, ResultActivity::class.java)
        intent.putExtra("isCorrect", isCorrect)
        intent.putExtra("randomNumber", randomNumber)

        finish()
        startActivity(intent)

    }

}