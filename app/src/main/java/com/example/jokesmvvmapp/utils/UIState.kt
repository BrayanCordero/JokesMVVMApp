package com.example.jokesmvvmapp.utils

import com.example.jokesmvvmapp.models.JokesResponse
//4) this handles the response we get back from the server.


//pass dynamic parameters to child
sealed class UIState{
    object LOADING : UIState()
    data class SUCCESS<T>(val response: T): UIState()
    data class ERROR(val error: Exception): UIState()
}


//static fields for state of response
enum class STATE(val response: String){
    LOADING(""),
    SUCCESS(""),
    ERROR("")
}
