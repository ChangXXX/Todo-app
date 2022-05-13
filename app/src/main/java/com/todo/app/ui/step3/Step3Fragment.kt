package com.todo.app.ui.step3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.todo.app.databinding.FragmentStep3Binding
import com.todo.app.ui.common.TodoAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Step3Fragment : Fragment() {
    private val viewModel: Step3ViewModel by viewModels()
    private lateinit var binding: FragmentStep3Binding
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStep3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        setListAdapter()
        initObservers()
    }

    private fun initObservers() {
        lifecycleScope.launch {
            launch {
                viewModel.addTodoEvent.collect {
                    val action = Step3FragmentDirections.actionStep3ToAdd()
                    findNavController().navigate(action)
                }
            }
            launch {
                viewModel.todos.collect{ todos ->
                    todoAdapter.submitList(todos)
                }
            }
        }
    }

    private fun setListAdapter() {
        todoAdapter = TodoAdapter()
        binding.rvStep3List.adapter = todoAdapter
    }

}