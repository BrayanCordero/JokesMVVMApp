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
import javax.inject.Inject
import kotlin.Exception

//3) can have as many view models as needed

@HiltViewModel
class JokesViewModel @Inject constructor(
    private val jokesRepository: JokesRepository
): ViewModel(){

    //mutable liveData can read,write, and modify values
    private val _jokes: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    //liveData can only read values
    val jokes: LiveData<UIState> get() = _jokes

    private val _randomJoke: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val randomJoke: LiveData<UIState> get() = _randomJoke

    private val _changeName: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val changeName: LiveData<UIState> get() = _changeName

    private var _firstName:String =""
    private var _lastName:String? = ""

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

    fun getRandomJoke(){
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val response = jokesRepository.getRandomJoke()
                if(response.isSuccessful){
                    response.body()?.let {
                        withContext(Dispatchers.Main){
                            _randomJoke.value= UIState.SUCCESS(it.value?.joke)
                        }
                    }?:throw Exception("DATA IS NULL")
                }else{
                    throw Exception(response.errorBody()?.string())
                }
            }catch (e:Exception){
                withContext(Dispatchers.Main){
                    _randomJoke.postValue(UIState.ERROR(e))
                }
            }
        }
    }

    fun changeName(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = jokesRepository.changeName(_firstName, _lastName)
                if (response.isSuccessful){
                    response.body()?.let {
                        withContext(Dispatchers.Main){
                            _changeName.value = UIState.SUCCESS(it.value?.joke)
                        }
                    }?:throw Exception("DATA IS NULL")
                }else{
                    throw Exception(response.errorBody()?.string())
                }
            }catch (e:Exception){
                withContext(Dispatchers.Main){
                    _changeName.postValue(UIState.ERROR(e))
                }
            }
        }
    }

     fun setNames(firstName: String, lastName: String?){
        _firstName = firstName
        _lastName = lastName
    }

    override fun onCleared() {
        super.onCleared()
        //remove view model here

    }
}