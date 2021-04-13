package com.example.planetquiz

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.planetquiz.databinding.FragmentQuestionsBinding

private const val TAG  = "QuestionFragment"

enum class QuizType {
    LargestPlanet,
    MostMoon,
    SpinSide
}

class QuestionsFragment : Fragment() {
    private lateinit var binding: FragmentQuestionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_questions, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set onClickListener for each question, navigate to answer fragment.
        with(binding) {
            listOf(quizLargestPlanet, quizMostMoon, quizSpinSide).forEach { quiz ->
                quiz.setOnClickListener {
                    navigateToAnswerFragment(quiz)
                }
            }
        }
    }

    private fun navigateToAnswerFragment(quiz: View) {
        val quizType = when (quiz) {
            binding.quizLargestPlanet -> QuizType.LargestPlanet
            binding.quizMostMoon -> QuizType.MostMoon
            else -> QuizType.SpinSide
        }
        val action =
            QuestionsFragmentDirections.actionQuestionsFragmentToAnswersFragment(
                quizType
            )
        findNavController().navigate(action)
    }
}















