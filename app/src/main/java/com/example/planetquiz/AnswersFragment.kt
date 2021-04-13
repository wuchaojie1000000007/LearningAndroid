package com.example.planetquiz

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.example.planetquiz.databinding.FragmentAnswersBinding

private const val TAG = "AnswerFragment"

class AnswersFragment : Fragment() {

    private lateinit var binding: FragmentAnswersBinding
    private val args by navArgs<AnswersFragmentArgs>()

    private lateinit var quizType: QuizType
    private lateinit var quiz: String
    private lateinit var answer: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_answers, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        quizType = args.quizType
        quiz = getQuiz(quizType)
        answer = getAnswer(quizType)

        with(binding) {

            // Set Quiz Text.
            quizText.text = quiz

            // Set clickListener for each button to show the result.
            listOf(
                mercury,
                venus,
                earth,
                mars,
                jupiter,
                saturn,
                uranus,
                neptune
            ).forEach { planet ->
                planet.setOnClickListener {
                    Log.d(TAG, "${planet.text} Clicked!")
                    showResult(quizType, planet)
                }
            }
        }
    }

    private fun getQuiz(quizType: QuizType) = when (quizType) {
        QuizType.LargestPlanet -> getString(R.string.quiz_largest_planet)
        QuizType.MostMoon -> getString(R.string.quiz_most_moon)
        QuizType.SpinSide -> getString(R.string.quiz_spin_side)
    }

    private fun getAnswer(quizType: QuizType) = when (quizType) {
        QuizType.LargestPlanet -> getString(R.string.ans_largest_planet)
        QuizType.MostMoon -> getString(R.string.ans_most_moon)
        QuizType.SpinSide -> getString(R.string.ans_spin_side)
    }

    private fun checkCorrect(quizType: QuizType, answer: View) =
        (quizType == QuizType.LargestPlanet && answer == binding.jupiter) ||
                (quizType == QuizType.MostMoon && answer == binding.saturn) ||
                (quizType == QuizType.SpinSide && answer == binding.uranus)
    private fun showResult(quizType: QuizType, planet: View) {
        val correct = checkCorrect(quizType, planet)
        val resultText = getString(
            R.string.res_text,
            if (correct) "YOU ARE RIGHT!" else "WRONG!",
            answer
        )
        binding.resText.text = resultText
        binding.resText.visibility = View.VISIBLE
    }

}
