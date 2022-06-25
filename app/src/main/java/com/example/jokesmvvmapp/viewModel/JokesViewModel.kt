package com.example.jokesmvvmapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jokesmvvmapp.network.JokesRepository
import com.example.jokesmvvmapp.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject
//3) can have as many view models as needed

@HiltViewModel
class JokesViewModel @Inject constructor(
    private val jokesRepository: JokesRepository
): ViewModel(){

    //mutable liveData can read,write, and modify values
    private val _jokes: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    //liveData can only read values
    val jokes: LiveData<UIState> get() = _jokes

    fun getAllJokes(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = jokesRepository.getAllJokes()
                if(response.isSuccessful){
                    response.body()?.let {
                        withContext(Dispatchers.Main){
                            //this updates UI
                            _jokes.value = UIState.SUCCESS(it)
                        }
                    }?:throw Exception("DATA IS NULL")
                }else{
                    throw Exception(response.errorBody()?.string())
                }

            }
            catch (e: Exception){
                withContext(Dispatchers.Main){
                    _jokes.postValue(UIState.ERROR(e))
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        //remove view model here
    }
}