package com.example.jokesmvvmapp.view

import android.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.jokesmvvmapp.viewModel.JokesViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
open class BaseFragment: Fragment() {


    protected val jokesViewModel by lazy{
        ViewModelProvider(requireActivity())[JokesViewModel::class.java]
    }

    protected fun showError(message: String, action: () -> Unit) {
        AlertDialog.Builder(requireContext())
            .setTitle("Error has occurred")
            .setMessage(message)
            .setPositiveButton("RETRY") { dialog, _ ->
                action.invoke()
            }
            .setNegativeButton("DISMISS") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
}