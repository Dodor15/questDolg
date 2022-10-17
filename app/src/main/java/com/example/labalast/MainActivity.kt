package com.example.labalast

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
private const val KEY_INDEX = "index"
class MainActivity : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var backButton: ImageButton
    private lateinit var textQuestion: TextView
    private var count = 0
    private var cv = 0
    private var proc = 0.0
    private fun checkAnswer(userAnswer:Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId,
            Toast.LENGTH_SHORT).show()
    }
    val quizViewModel: QuizViewModel by
    lazy {
        ViewModelProviders.of(this).get(QuizViewModel::class.java)
    }
    private fun updateQuestion() {
        val questionTextResId =
            quizViewModel.currentQuestionText
        textQuestion.setText(questionTextResId)


}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(ContentValues.TAG,"On Create")
        setContentView(R.layout.activity_main)

        val currentIndex =
            savedInstanceState?.getInt(KEY_INDEX, 0) ?: 0
        quizViewModel.currentIndex = currentIndex
        trueButton = findViewById(R.id.true_button)

        trueButton.setOnClickListener { view: View
            ->
            checkAnswer(true)
            quizViewModel.countPlus()
            quizViewModel.count(true)
            trueButton.setEnabled(false)
            falseButton.setEnabled(false)
            nextButton.setEnabled(true)
            textQuestion.setEnabled(true)

        }
        falseButton = findViewById(R.id.false_button)
        falseButton.setOnClickListener { view: View
            ->
            checkAnswer(false)
            quizViewModel.countPlus()
            quizViewModel.count(false)
            trueButton.setEnabled(false)
            falseButton.setEnabled(false)
            nextButton.setEnabled(true)
            textQuestion.setEnabled(true)
        }
        nextButton =
            findViewById(R.id.next_Button)
        nextButton.setEnabled(false)
        backButton =
            findViewById(R.id.back_Button)
        backButton.setEnabled(false)
        textQuestion =
            findViewById(R.id.textQuestion)
        textQuestion.setEnabled(false)

        backButton.setOnClickListener{

            backButton
            if(currentIndex>0) {



                updateQuestion()
            }

        }
        updateQuestion()
        if(quizViewModel.count2==quizViewModel.questionBank.size)
        {
            nextButton.setEnabled(false)
            textQuestion.setEnabled(false)
            trueButton.setEnabled(false)
            falseButton.setEnabled(false)
        }
        else if(quizViewModel.blockbutton==1)
        {
            trueButton.setEnabled(false)
            falseButton.setEnabled(false)
            nextButton.setEnabled(true)
            textQuestion.setEnabled(true)
        }
        else if(quizViewModel.blockbutton==0)
        {
            trueButton.setEnabled(true)
            falseButton.setEnabled(true)
            nextButton.setEnabled(false)
            textQuestion.setEnabled(false)
        }

        nextButton.setOnClickListener {
            quizViewModel.countMinus()
            quizViewModel.ButtonsEnabled()
            quizViewModel.moveToNext()
            trueButton.setEnabled(true)
            falseButton.setEnabled(true)
            nextButton.setEnabled(false)
            textQuestion.setEnabled(false)
            if(quizViewModel.count2 ==quizViewModel.questionBank.size)
            {
                nextButton.setEnabled(false)
                textQuestion.setEnabled(false)
                trueButton.setEnabled(false)
                falseButton.setEnabled(false)
                proc = (quizViewModel.count.toDouble()/quizViewModel.questionBank.size)*100
                Toast.makeText(this,proc.toString(), Toast.LENGTH_SHORT).show()
            }

            updateQuestion()
        }
        updateQuestion()

        textQuestion.setOnClickListener {
            quizViewModel.countMinus()
            quizViewModel.ButtonsEnabled()
            quizViewModel.moveToNext()
            trueButton.setEnabled(true)
            falseButton.setEnabled(true)
            textQuestion.setEnabled(false)
            nextButton.setEnabled(false)
            if(quizViewModel.count2==quizViewModel.questionBank.size)
            {

                nextButton.setEnabled(false)
                textQuestion.setEnabled(false)
                trueButton.setEnabled(false)
                falseButton.setEnabled(false)
                proc = (quizViewModel.count.toDouble()/quizViewModel.questionBank.size)*100
                Toast.makeText(this,proc.toString(), Toast.LENGTH_SHORT).show()
            }
            updateQuestion()
        }
        updateQuestion()


    }


    override fun onStart() {
        super.onStart()
        Log.d(ContentValues.TAG,"On Start")

    }
    override fun onResume() {
        super.onResume()
        Log.d(ContentValues.TAG,"On Resume")

    }
    override fun onPostResume() {
        super.onPostResume()
        Log.d(ContentValues.TAG,"On PostResume")

    }
    override fun onPause() {
        super.onPause()
        Log.d(ContentValues.TAG,"On Pause")

    }
    override fun
            onSaveInstanceState(savedInstanceState: Bundle)
    {
        super.onSaveInstanceState(savedInstanceState)
        Log.i(TAG,
            "onSaveInstanceState")
        savedInstanceState.putInt(KEY_INDEX,
            quizViewModel.currentIndex)
    }
    override fun onStop() {
        super.onStop()
        Log.d(ContentValues.TAG,"On Stop")

    }

    override fun onRestart() {
        super.onRestart()
        Log.d(ContentValues.TAG,"On Restart")

    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(ContentValues.TAG,"On Destroy")

    }

}