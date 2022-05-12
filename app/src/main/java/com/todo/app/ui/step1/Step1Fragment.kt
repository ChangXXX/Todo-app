package com.todo.app.ui.step1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.todo.app.common.EventObserver
import com.todo.app.common.TodoAdapter
import com.todo.app.databinding.FragmentStep1Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Step1Fragment : Fragment() {
    private val viewModel: Step1ViewModel by viewModels()
    private lateinit var binding: FragmentStep1Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStep1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        setNavigationBack()
        setListAdapter()
    }

    private fun setNavigationBack() {
        binding.tbStep1.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }


    private fun setListAdapter() {
        val todoAdapter = TodoAdapter()
        binding.rvStep1List.adapter = todoAdapter
        viewModel.todos.observe(viewLifecycleOwner) {
            todoAdapter.submitList(it)
        }
    }
}