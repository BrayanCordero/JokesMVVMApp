package com.example.jokesmvvmapp.view

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jokesmvvmapp.Domain.DomainJoke
import com.example.jokesmvvmapp.R
import com.example.jokesmvvmapp.databinding.FragmentRandomJokeBinding
import com.example.jokesmvvmapp.models.Joke
import com.example.jokesmvvmapp.models.JokesResponse
import com.example.jokesmvvmapp.models.RandomJoke
import com.example.jokesmvvmapp.utils.UIState


class RandomJokeFragment : BaseFragment(){

    private val binding by lazy{
        FragmentRandomJokeBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        binding.randomJokeBtn.setOnClickListener {
            jokesViewModel.getRandomJoke()
            jokesViewModel.randomJoke.observe(viewLifecycleOwner){ state->
                when(state){
                    is UIState.LOADING-> {

                    }
                    is UIState.SUCCESS<*> -> {
//                        (state as UIState.SUCCESS<JokesResponse>).response
                        binding.randomJokeText.text = state.response.toString()



                    }
                    is UIState.ERROR-> {

                        state.error.localizedMessage?.let {
                            showError(it){
                                jokesViewModel.getRandomJoke()
                            }
                        }
                    }
                }
            }
        }






        return binding.root
    }

}