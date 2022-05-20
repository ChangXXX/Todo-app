package com.todo.app.ui.step5

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
import com.todo.app.databinding.FragmentStep5Binding
import com.todo.app.ui.common.TodoAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Step5Fragment : Fragment() {
    private val viewModel: Step5ViewModel by viewModels()
    private lateinit var binding: FragmentStep5Binding
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStep5Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setListAdapter()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.eventflow.collect { event -> handleEvent(event) }
                }
                viewModel.loadTodos()
            }
        }
    }

    private fun setListAdapter() {
        todoAdapter = TodoAdapter()
        binding.rvStep5List.adapter = todoAdapter
    }

    private fun handleEvent(event: Step5ViewModel.Step5Event) = when (event) {
        is Step5ViewModel.Step5Event.TodoList -> {
            todoAdapter.submitList(event.todos)
        }
        is Step5ViewModel.Step5Event.ShowToast -> {
            Toast.makeText(context, event.text, Toast.LENGTH_SHORT).show()
        }
        is Step5ViewModel.Step5Event.OpenAddFragment -> {
            val action = Step5FragmentDirections.actionStep5ToAdd()
            findNavController().navigate(action)
        }
    }
}