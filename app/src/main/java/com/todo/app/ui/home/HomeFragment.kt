package com.todo.app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.todo.app.ui.common.EventObserver
import com.todo.app.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        setupNavigation()
    }

    private fun setupNavigation() {
        viewModel.openStepOneFragmentEvent.observe(viewLifecycleOwner, EventObserver {
            val action =
                HomeFragmentDirections.actionHomeToStep1()
            findNavController().navigate(action)
        })

        viewModel.openStepTwoFragmentEvent.observe(viewLifecycleOwner, {
            val action =
                HomeFragmentDirections.actionHomeToStep2()
            findNavController().navigate(action)
        })

        // TODO STEP 3~6 navigation
    }
}