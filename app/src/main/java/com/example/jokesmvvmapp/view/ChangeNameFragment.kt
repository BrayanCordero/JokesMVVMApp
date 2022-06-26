package com.example.jokesmvvmapp.view

import android.os.Bundle
import android.provider.Contacts
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.example.jokesmvvmapp.R
import com.example.jokesmvvmapp.databinding.FragmentChangeNameBinding
import com.example.jokesmvvmapp.utils.UIState


class ChangeNameFragment : BaseFragment() {



    private val binding by lazy{
        FragmentChangeNameBinding.inflate(layoutInflater)
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



        binding.changeNameBtn.setOnClickListener {
            jokesViewModel.setNames(binding.changeFirstName.text.toString(),binding.changeLastName.text.toString())
            jokesViewModel.changeName()
            jokesViewModel.changeName.observe(viewLifecycleOwner){state->
                when(state){
                    is UIState.LOADING->{

                    }
                    is UIState.SUCCESS<*>->{
                         binding.changeNameJoke.text = state.response.toString()
                    }
                    is UIState.ERROR->{
                        state.error.localizedMessage?.let {
                            showError(it){
                                jokesViewModel.changeName()
                            }
                        }
                    }
                }
            }

        }


        return binding.root
    }



}