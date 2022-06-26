package com.example.jokesmvvmapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.jokesmvvmapp.adapter.JokesAdapter
import com.example.jokesmvvmapp.databinding.FragmentNeverendingListBinding
import com.example.jokesmvvmapp.models.JokesResponse

import com.example.jokesmvvmapp.utils.UIState



class NeverEndingFragment : BaseFragment() {

    private val binding by lazy{
        FragmentNeverendingListBinding.inflate(layoutInflater)
    }

    private val jokesAdapter by lazy{
        JokesAdapter{

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding.jokesRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = jokesAdapter
        }


        jokesViewModel.jokes.observe(viewLifecycleOwner){ state->
            when(state){
                is UIState.LOADING-> {
                    binding.loadingSpinner.visibility = View.VISIBLE
                    binding.jokesRecycler.visibility = View.GONE
                }
                is UIState.SUCCESS<*> -> {
                    (state as UIState.SUCCESS<JokesResponse>).response
                    binding.loadingSpinner.visibility = View.GONE
                    binding.jokesRecycler.visibility = View.VISIBLE

                    jokesAdapter.updateNewJokes(state.response.value?: emptyList())
                }
                is UIState.ERROR-> {
                    binding.loadingSpinner.visibility = View.GONE
                    binding.jokesRecycler.visibility = View.GONE

                    state.error.localizedMessage?.let {
                        showError(it){
                            jokesViewModel.getAllJokes()
                        }
                    }
                }
            }
        }

        jokesViewModel.getAllJokes()




        return binding.root
    }

//    override fun onStop() {
//        super.onStop()
//        jokesViewModel.jokes.removeObserver(viewLifecycleOwner)
//    }


}