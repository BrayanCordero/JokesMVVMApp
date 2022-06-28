package com.example.jokesmvvmapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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
    private var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val linearLayout = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.jokesRecycler.apply {
            layoutManager = linearLayout
            adapter = jokesAdapter
        }


        jokesViewModel.jokes.observe(viewLifecycleOwner){ state->
            when(state){
                is UIState.LOADING-> {
                    isLoading = true
                    binding.loadingSpinner.visibility = View.VISIBLE
                    binding.jokesRecycler.visibility = View.GONE
                }
                is UIState.SUCCESS<*> -> {
                    (state as UIState.SUCCESS<JokesResponse>).response
                    binding.loadingSpinner.visibility = View.GONE
                    binding.jokesRecycler.visibility = View.VISIBLE


                    jokesAdapter.updateNewJokes(state.response.value?: emptyList())
                    isLoading = false
                }
                is UIState.ERROR-> {
                    binding.loadingSpinner.visibility = View.GONE
                    binding.jokesRecycler.visibility = View.GONE

                    state.error.localizedMessage?.let {
                        showError(it){
                            jokesViewModel.get20RandomJokes()
                        }
                    }
                }
            }
        }


        binding.jokesRecycler.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                            val visibleItemCount: Int = binding.jokesRecycler.layoutManager!!.childCount
                            val lastVisibleItem: Int = linearLayout.findLastVisibleItemPosition()
                            val total = linearLayout.itemCount
                Log.d("Scroll","$visibleItemCount")
                Log.d("Scroll","$lastVisibleItem")

                if(!isLoading){
                    if(visibleItemCount+ lastVisibleItem >= total){
                        jokesViewModel.get20RandomJokes()

                    }
                }
            }
        })

        jokesViewModel.get20RandomJokes()

        return binding.root
    }

    override fun onStop() {
        super.onStop()
        jokesAdapter.clearJokes()

    }

//    override fun onStop() {
//        super.onStop()
//        jokesViewModel.jokes.removeObserver(viewLifecycleOwner)
//    }


}