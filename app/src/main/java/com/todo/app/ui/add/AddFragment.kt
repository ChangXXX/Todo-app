package com.todo.app.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.todo.app.data.model.Todo
import com.todo.app.databinding.FragmentAddBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddFragment : Fragment() {
    private val viewModel: AddViewModel by viewModels()
    private lateinit var binding: FragmentAddBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        setNavigationBack()
        initObservers()
    }

    private fun setNavigationBack() {
        binding.tbAdd.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun initObservers() {
        viewModel.addTodoEvent.observe(viewLifecycleOwner, {
            val title = binding.edtTodoTitle.text.toString()
            val contents = binding.edtTodoContents.text.toString()
            val room = binding.radiobtnRoom.isChecked

            if (title.isEmpty() || contents.isEmpty()) {
                Toast.makeText(context, "TITLE or CONTENTS is Empty", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.addTodo(Todo(title, contents), room)
                Toast.makeText(context, "Making Todo", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            }
        })
    }
}