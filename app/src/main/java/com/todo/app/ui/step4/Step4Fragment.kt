package com.todo.app.ui.step4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.todo.app.databinding.FragmentStep4Binding
import com.todo.app.ui.common.TodoAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Step4Fragment : Fragment() {
    private val viewModel: Step4ViewModel by viewModels()
    private lateinit var binding: FragmentStep4Binding
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStep4Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        setListAdapter()
        lifecycleScope.launch {
            viewModel.eventflow.collect { event -> handleEvent(event) }
        }
    }

    private fun setListAdapter() {
        todoAdapter = TodoAdapter()
        binding.rvStep4List.adapter = todoAdapter
    }

    private fun handleEvent(event: Step4ViewModel.Event) = when (event) {
        is Step4ViewModel.Event.TodoList -> {
            todoAdapter.submitList(event.todos)
        }
        is Step4ViewModel.Event.ShowToast -> {
            Toast.makeText(context, event.text, Toast.LENGTH_SHORT).show()
        }
        is Step4ViewModel.Event.OpenAddFragment -> {
            val action =
                Step4FragmentDirections.actionStep4ToAdd()
            findNavController().navigate(action)
        }
    }
}