package com.todo.app.ui.step2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.todo.app.ui.common.TodoAdapter
import com.todo.app.databinding.FragmentStep2Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Step2Fragment : Fragment() {
    private val viewModel: Step2ViewModel by viewModels()
    private lateinit var binding: FragmentStep2Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStep2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        setNavigationBack()
        initObservers()
        setListAdapter()
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadTodos()
    }

    private fun setNavigationBack() {
        binding.tbStep2.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun initObservers() {
        viewModel.addTodoEvent.observe(viewLifecycleOwner, {
            val action = Step2FragmentDirections.actionStep2ToAdd()
            findNavController().navigate(action)
        })
    }

    private fun setListAdapter() {
        val todoAdapter = TodoAdapter()
        binding.rvStep2List.adapter = todoAdapter
        viewModel.todos.observe(viewLifecycleOwner, {
            todoAdapter.submitList(it)
        })
    }
}