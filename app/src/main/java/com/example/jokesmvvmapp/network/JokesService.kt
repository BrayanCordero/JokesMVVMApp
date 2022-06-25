package com.example.jokesmvvmapp.network

import com.example.jokesmvvmapp.models.Joke
import com.example.jokesmvvmapp.models.JokesResponse
import retrofit2.Response
import retrofit2.http.GET


//1) network to make calls to api
interface JokesService {

    @GET(BASE_URL)
    suspend fun getAllJokes():Response<JokesResponse>

    @GET(CHANGE_NAME_URL)
    suspend fun changeName(firstName:String, lastName:String?=null):Response<JokesResponse>

    @GET()
    suspend fun getRandomJoke():Response<Joke>

//for get a joke by id need to put @PATH("path_name") name:Type?= null as a parameter
    // ex) getRandomJoke(@PATH("id") id:String? = null


    companion object{
        const val BASE_URL ="http://api.icndb.com/jokes/"

        //random http://api.icndb.com/jokes/random
        private const val RANDOM_URL = "random"

        //change name http://api.icndb.com/jokes/random?firstName=John&lastName=Doe
        private const val CHANGE_NAME_URL="random?firstName={firstName}&lastName={lastName}"

        //specific joke url http://api.icndb.com/jokes/<id>
//        private const val ID_URL = "{id}"


    }
}