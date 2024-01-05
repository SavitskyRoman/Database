package com.example.database

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface.BOLD
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.SpannedString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.graphics.toColorInt
import androidx.core.text.getSpans
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

import com.example.database.databinding.FragmentWritingBinding
class WritingFragment : Fragment() {
    private var _binding: FragmentWritingBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: WritingViewModel
    lateinit var viewModelFactory: WritingViewModelFactory

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWritingBinding.inflate(inflater, container, false)
        val view = binding.root

        val firstArrayCloneForWritting =
            WritingFragmentArgs.fromBundle(requireArguments()).firstArrayCloneForWritting
        val secondArrayCloneForWritting =
            WritingFragmentArgs.fromBundle(requireArguments()).secondArrayCloneForWritting
        val unitName = WritingFragmentArgs.fromBundle(requireArguments()).unitName
        viewModelFactory =
            WritingViewModelFactory(firstArrayCloneForWritting, secondArrayCloneForWritting, unitName)
        viewModel = ViewModelProvider(this, viewModelFactory).get(WritingViewModel::class.java)

        binding.writingViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.toolbarWritingFragment.setNavigationIcon(R.drawable.ic_baseline_close_24)
        binding.toolbarWritingFragment.setOnClickListener { view -> view.findNavController()
            .navigate(R.id.action_writingFragment_to_tasksFragment)}


        viewModel.russianWordOfSecret.observe(viewLifecycleOwner, Observer { newValue ->
            var text = getString(R.string.task, newValue)
            var spannable = SpannableString(text)
            spannable.setSpan(
                StyleSpan(BOLD),
                17, 18+ viewModel.russianWordOfSecret.value!!.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            binding.conditionForWriting.text = spannable
        })

//        "@{@string/incorrect_user_write(writingViewModel.fromEditText, writingViewModel.englishWordOfSecret)}"

        viewModel.count.observe(viewLifecycleOwner, Observer { newValue ->
            if (newValue == 0) {
                val action = WritingFragmentDirections.actionWritingFragmentToQuizFragment2(viewModel.unitName)
                view.findNavController().navigate(action)
            }
        })
//        @{@string/task(writingViewModel.russianWordOfSecret)}
//        viewModel.russianWordOfSecret.observe(viewLifecycleOwner, Observer { newValue ->
//           val text = getText(R.string.task) as SpannedString
//            val annotations = text.getSpans(0, text.length, android.text.Annotation::class.java)
//            val spannedString = SpannableString(text)
//            for(annotation in annotations){
//                if (annotation.key == "font"){
//                    val fontName = annotation.value
//                    if (fontName == "bold"){
//                        spannedString.setSpan(
//                            StyleSpan(BOLD),
//                            text.getSpanStart(annotation),
//                            text.getSpanEnd(annotation),
//                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
//                        )
//                    }
//                }
//            }
//
//
//        })

        binding.buttonTest.setOnClickListener {
            val action = WritingFragmentDirections.actionWritingFragmentToQuizFragment2(viewModel.unitName)
            view.findNavController().navigate(action)

        }

        viewModel.isUserWrong.observe(viewLifecycleOwner, Observer {
            newValue ->
            if(newValue){
                var text = getString(R.string.incorrect_user_write, viewModel.fromEditText,viewModel.englishWordOfSecret)
                var spannable = SpannableString(text)

                spannable.setSpan(
                    ForegroundColorSpan(Color.rgb(221,91,86)),
                    9, 10+ viewModel.fromEditText.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                spannable.setSpan(
                    StyleSpan(BOLD),
                    9, 10+ viewModel.fromEditText.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )

                spannable.setSpan(
                    ForegroundColorSpan(Color.rgb(98,200,165)),
                    29+ viewModel.fromEditText.length, 29+ viewModel.fromEditText.length + viewModel.englishWordOfSecret.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                spannable.setSpan(
                   StyleSpan(BOLD),
                    29+ viewModel.fromEditText.length, 29+ viewModel.fromEditText.length + viewModel.englishWordOfSecret.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )

                binding.userIncorrectWrite.text = spannable
            }
            else binding.userIncorrectWrite.text = null
        })

        binding.buttonSendAnswerUser.setOnClickListener {
            viewModel.checkAnswerUserWriting()
//            Toast.makeText(
//                binding.root.context,
//                viewModel.rightOrWrong,
//                Toast.LENGTH_SHORT
//            ).show()
                binding.editTextFromUser.text = null

        }
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (isEnabled) {
                        view.findNavController().navigate(R.id.action_writingFragment_to_tasksFragment)
                        isEnabled = false
//                        requireActivity().onBackPressed()
                    }
                }
            }
            )
        return view
    }


//class WritingFragment : Fragment() {
//    private var _binding: FragmentWritingBinding? = null
//    private val binding get() = _binding!!
//    lateinit var viewModel: WritingViewModel
//    lateinit var viewModelFactory: WritingViewModelFactory
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        _binding = FragmentWritingBinding.inflate(inflater, container, false)
//        val view = binding.root
//
//        val firstArrayCloneForWritting =
//            WritingFragmentArgs.fromBundle(requireArguments()).firstArrayCloneForWritting
//        val secondArrayCloneForWritting =
//            WritingFragmentArgs.fromBundle(requireArguments()).secondArrayCloneForWritting
//        val unitName = WritingFragmentArgs.fromBundle(requireArguments()).unitName
//        viewModelFactory =
//            WritingViewModelFactory(firstArrayCloneForWritting, secondArrayCloneForWritting, unitName)
//        viewModel = ViewModelProvider(this, viewModelFactory).get(WritingViewModel::class.java)
//        viewModel.firstStart()
//        viewModel.writing()
//
//        binding.writingViewModel = viewModel
//        binding.lifecycleOwner = viewLifecycleOwner
//
//        binding.toolbarWritingFragment.setNavigationIcon(R.drawable.ic_baseline_close_24)
//        binding.toolbarWritingFragment.setOnClickListener { view -> view.findNavController()
//            .navigate(R.id.action_writingFragment_to_tasksFragment)}
//
//        viewModel.count.observe(viewLifecycleOwner, Observer { newValue ->
//            if (newValue == 0) {
//                val action = WritingFragmentDirections.actionWritingFragmentToQuizFragment2(viewModel.unitName)
//                view.findNavController().navigate(action)
//            }
//        })
//
//        binding.buttonTest.setOnClickListener {
//            val action = WritingFragmentDirections.actionWritingFragmentToQuizFragment2(viewModel.unitName)
//            view.findNavController().navigate(action)
//
//        }
//
//        binding.buttonSendAnswerUser.setOnClickListener {
//            viewModel.checkAnswerUserWriting()
//            Toast.makeText(
//                binding.root.context,
//                viewModel.rightOrWrong,
//                Toast.LENGTH_SHORT
//            ).show()
//
//            if(viewModel.count.value != 0) {viewModel.writing()
//                binding.editTextFromUser.text = null
//                       }
//        }
//        return view
//    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}