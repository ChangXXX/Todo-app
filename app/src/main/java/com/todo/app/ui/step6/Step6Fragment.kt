package com.todo.app.ui.step6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.todo.app.databinding.FragmentStep6Binding
import com.todo.app.ui.common.TodoAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Step6Fragment : Fragment() {
    private val viewModel: Step6ViewModel by viewModels()
    private lateinit var binding: FragmentStep6Binding
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStep6Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setListAdapter()

        setListAdapter()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.eventFlow.collect { event -> handleEvent(event) }
                }
                viewModel.loadTodos()
            }
        }
    }

    private fun setListAdapter() {
        todoAdapter = TodoAdapter()
        binding.rvStep6List.adapter = todoAdapter
    }

    private fun handleEvent(event: Step6ViewModel.Step6Event) = when (event) {
        is Step6ViewModel.Step6Event.TodoList -> {
            todoAdapter.submitList(event.todos)
        }
        is Step6ViewModel.Step6Event.ShowToast -> {
            Toast.makeText(context, event.text, Toast.LENGTH_SHORT).show()
        }
        is Step6ViewModel.Step6Event.OpenAddFragment -> {
            val action =
                Step6FragmentDirections.actionStep6ToAdd()
            findNavController().navigate(action)
        }
    }
}