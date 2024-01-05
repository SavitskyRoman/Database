package com.example.database

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.os.Build.VERSION_CODES.Q
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.view.size
import androidx.fragment.app.FragmentManager.TAG
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.database.databinding.FragmentQuizBinding


class QuizFragment : Fragment() {
    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: QuizViewModel


    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val dao = TaskDatabase.getInstance(application).taskDao
        val unitName = QuizFragmentArgs.fromBundle(requireArguments()).unitName
        val viewModelFactory = QuizViewModelFactory(unitName, dao)
        viewModel = ViewModelProvider(this, viewModelFactory).get(QuizViewModel::class.java)

        binding.toolbarQuizFragment.setNavigationIcon(R.drawable.ic_baseline_close_24)
        binding.toolbarQuizFragment.setOnClickListener { view -> view.findNavController()
            .navigate(R.id.action_quizFragment_to_tasksFragment)}

        viewModel.tasks.observe(viewLifecycleOwner, Observer {
            it?.let {

                if (viewModel.isCanStart.value == false) {
                    viewModel.taskForDelArray = it
                    viewModel.test()
                    if (viewModel.firstArray.size != 0) viewModel.firstStart()
                    if (viewModel.firstArray.size == 0) {
                        view.findNavController().navigate(R.id.action_quizFragment_to_tasksFragment)
                    }
                }
            }
        })



        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.isCanStart.observe(viewLifecycleOwner, Observer { newValue ->
            if (newValue) {
                viewModel.counter()
                viewModel.count.observe(viewLifecycleOwner, Observer { newValue ->
                    if (newValue == 0 && viewModel.firstArray.size != 0) {
                        viewModel.updateTaskTwo()

                        viewModel.arrayToWritingFragment()

                        val action = QuizFragmentDirections.actionQuizFragmentToWritingFragment(
                            viewModel.firstArrayCloneForWrittingToFragment,
                            viewModel.secondArrayCloneForWrittingToFragment,
                            viewModel.unitNametoWritingFragment
                        )
                        view.findNavController().navigate(action)
                    }
                })
            }
        })

        viewModel.isColorBackground.observe(viewLifecycleOwner, Observer { newValue ->
            if (newValue == true) {
                if (viewModel.userAnswer == 0) {
                    binding.firstCase.setBackgroundResource(R.drawable.bg_text_view_case_1_4_eror)
                } else if (viewModel.userAnswer == 1) {
                    binding.secondCase.setBackgroundResource(R.drawable.bg_text_view_case_1_4_eror)
                } else if (viewModel.userAnswer == 2) {
                    binding.thirdCase.setBackgroundResource(R.drawable.bg_text_view_case_1_4_eror)
                } else binding.fourthCase.setBackgroundResource(R.drawable.bg_text_view_case_1_4_eror)

                if (viewModel.forChangeColourIfUserMistake() == 0) {
                    binding.firstCase.setBackgroundResource(R.drawable.bg_text_view_case_1_4_answer)
                } else if (viewModel.forChangeColourIfUserMistake() == 1) {
                    binding.secondCase.setBackgroundResource(R.drawable.bg_text_view_case_1_4_answer)
                } else if (viewModel.forChangeColourIfUserMistake() == 2) {
                    binding.thirdCase.setBackgroundResource(R.drawable.bg_text_view_case_1_4_answer)
                } else binding.fourthCase.setBackgroundResource(R.drawable.bg_text_view_case_1_4_answer)


                binding.layout.setOnClickListener {
                    if (viewModel.isColorBackground.value == true) {
                        binding.firstCase.setBackgroundResource(R.drawable.bg_text_view_case_1_4)
                        binding.secondCase.setBackgroundResource(R.drawable.bg_text_view_case_1_4)
                        binding.thirdCase.setBackgroundResource(R.drawable.bg_text_view_case_1_4)
                        binding.fourthCase.setBackgroundResource(R.drawable.bg_text_view_case_1_4)
                        viewModel.forStartAfterUsersMistake()
                    }
                }

            }
        })





        binding.firstCase.setOnClickListener {
            if(viewModel.isColorBackground.value == false){
                viewModel.userAnswer = 0
                viewModel.checkAnswerUserQuiz()
                Toast.makeText(
                    binding.root.context,
                    viewModel.rightOrWrong,
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                binding.firstCase.setBackgroundResource(R.drawable.bg_text_view_case_1_4)
                binding.secondCase.setBackgroundResource(R.drawable.bg_text_view_case_1_4)
                binding.thirdCase.setBackgroundResource(R.drawable.bg_text_view_case_1_4)
                binding.fourthCase.setBackgroundResource(R.drawable.bg_text_view_case_1_4)
                viewModel.forStartAfterUsersMistake()
            }
        }

        binding.secondCase.setOnClickListener {
            if(viewModel.isColorBackground.value == false){
                viewModel.userAnswer = 1
                viewModel.checkAnswerUserQuiz()
                Toast.makeText(
                    binding.root.context,
                    viewModel.rightOrWrong,
                    Toast.LENGTH_SHORT
                ).show()
            }         else {
                binding.firstCase.setBackgroundResource(R.drawable.bg_text_view_case_1_4)
                binding.secondCase.setBackgroundResource(R.drawable.bg_text_view_case_1_4)
                binding.thirdCase.setBackgroundResource(R.drawable.bg_text_view_case_1_4)
                binding.fourthCase.setBackgroundResource(R.drawable.bg_text_view_case_1_4)
                viewModel.forStartAfterUsersMistake()
            }

        }

        binding.thirdCase.setOnClickListener {
            if(viewModel.isColorBackground.value == false){
                viewModel.userAnswer = 2
                viewModel.checkAnswerUserQuiz()
                Toast.makeText(
                    binding.root.context,
                    viewModel.rightOrWrong,
                    Toast.LENGTH_SHORT
                ).show()
            }else {
                binding.firstCase.setBackgroundResource(R.drawable.bg_text_view_case_1_4)
                binding.secondCase.setBackgroundResource(R.drawable.bg_text_view_case_1_4)
                binding.thirdCase.setBackgroundResource(R.drawable.bg_text_view_case_1_4)
                binding.fourthCase.setBackgroundResource(R.drawable.bg_text_view_case_1_4)
                viewModel.forStartAfterUsersMistake()
            }
        }

        binding.fourthCase.setOnClickListener {
            if(viewModel.isColorBackground.value == false){
                viewModel.userAnswer = 3
                viewModel.checkAnswerUserQuiz()
                Toast.makeText(
                    binding.root.context,
                    viewModel.rightOrWrong,
                    Toast.LENGTH_SHORT
                ).show()
            }else {
                binding.firstCase.setBackgroundResource(R.drawable.bg_text_view_case_1_4)
                binding.secondCase.setBackgroundResource(R.drawable.bg_text_view_case_1_4)
                binding.thirdCase.setBackgroundResource(R.drawable.bg_text_view_case_1_4)
                binding.fourthCase.setBackgroundResource(R.drawable.bg_text_view_case_1_4)
                viewModel.forStartAfterUsersMistake()
            }
        }
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (isEnabled) {
                        view.findNavController().navigate(R.id.action_quizFragment_to_tasksFragment)
                        isEnabled = false
//                        requireActivity().onBackPressed()
                    }
                }
            }
            )
        return view
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}