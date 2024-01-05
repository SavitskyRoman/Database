package com.example.database

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.database.databinding.FragmentChangeCardBinding


class ChangeCardFragment : Fragment() {
private var _binding: FragmentChangeCardBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChangeCardBinding.inflate(inflater, container, false)
        val view = binding.root
        val taskId = ChangeCardFragmentArgs.fromBundle(requireArguments()).taskId

        val application = requireNotNull(this.activity).application
        val dao = TaskDatabase.getInstance(application).taskDao

        val viewModelFactory = ChangeCardViewModelFactory(taskId, dao)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ChangeCardViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.navigateToList.observe(viewLifecycleOwner, Observer { navigate ->
            if (navigate) {
                view.findNavController()
                    .navigate(R.id.action_changeCardFragment_to_tasksFragment)
                viewModel.onNavigatedToList()
            }
        })
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (isEnabled) {
                        view.findNavController().navigate(R.id.action_changeCardFragment_to_tasksFragment)
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