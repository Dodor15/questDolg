package com.example.labalast

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel

const val TAG = "QuizViewModel"
class QuizViewModel : ViewModel() {
    var currentIndex = 0
    var cv = 0
    var count = 0
    var count2 = 0
    var blockbutton = 0
    val questionBank = listOf(
        Question(R.string.question_australia,
            true),
        Question(R.string.question_oceans,
            true),
        Question(R.string.question_mideast,
            false),
        Question(R.string.question_africa,
            false),
        Question(R.string.question_americas,
            true),
        Question(R.string.question_asia, true)
    )
    val currentQuestionAnswer: Boolean
        get() =
            questionBank[currentIndex].answer
    val currentQuestionText: Int
        get() =
            questionBank[currentIndex].textResId
    fun moveToNext() {
        currentIndex = (currentIndex + 1) %
                questionBank.size
        cv++

    }
    fun count(userAnswer:Boolean)
    {
        val messageResId = if (userAnswer == currentQuestionAnswer) {
            count++
            R.string.correct_toast
        } else {
            R.string.incorrect_toast

        }
    }
    fun ButtonsEnabled()
    {
        count2++
    }
    fun countPlus()
    {
     blockbutton = 1
    }
    fun countMinus()
    {
        blockbutton = 0
    }

    }



