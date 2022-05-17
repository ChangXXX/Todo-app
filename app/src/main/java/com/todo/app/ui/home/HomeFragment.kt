package com.todo.app.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.todo.app.R
import com.todo.app.ui.common.EventObserver
import com.todo.app.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

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

        lifecycleScope.launch {
            launch {
                viewModel.openStepThreeFragmentEvent.collect {
                    if (findNavController().currentDestination?.id == R.id.navigation_home) {
                        val action =
                            HomeFragmentDirections.actionHomeToStep3()
                        findNavController().navigate(action)
                    }
                }
            }

            launch {
                viewModel.eventFlow.collect { event ->
                    handleEvent(event)
                }
            }
        }
    }

    private fun handleEvent(event: HomeViewModel.OpenFragmentEvent) = when (event) {
        is HomeViewModel.OpenFragmentEvent.StepFour -> {
            if (findNavController().currentDestination?.id == R.id.navigation_home) {
                val action =
                    HomeFragmentDirections.actionHomeToStep4()
                findNavController().navigate(action)
            } else {
                Log.d("NAVIGATION", "Destination is diff")
            }
        }
        is HomeViewModel.OpenFragmentEvent.StepFive -> {

        }
        is HomeViewModel.OpenFragmentEvent.StepSix -> {

        }
    }
}