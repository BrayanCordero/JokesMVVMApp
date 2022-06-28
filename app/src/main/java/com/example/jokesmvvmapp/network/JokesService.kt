package com.example.jokesmvvmapp.network

import com.example.jokesmvvmapp.models.Joke
import com.example.jokesmvvmapp.models.JokesResponse
import com.example.jokesmvvmapp.models.RandomJoke
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


//1) network to make calls to api
interface JokesService {

    @GET(BASE_URL)
    suspend fun getAllJokes():Response<JokesResponse>

    @GET(CHANGE_NAME_URL)
    suspend fun changeName(
        @Query("firstName")firstName:String,
        @Query("lastName") lastName:String?=null
    ):Response<RandomJoke>

    @GET(RANDOM_URL)
    suspend fun getRandomJoke():Response<RandomJoke>

    @GET(RANDOM_COUNT_URL)
    suspend fun get20Jokes(
        @Path("number") number: Int
    ): Response<JokesResponse>

//    @GET(EXCLUDE_EXPLICIT)
//    suspend fun excludeExplicit():Response<RandomJoke>

//for get a joke by id need to put @PATH("path_name") name:Type?= null as a parameter
    // ex) getRandomJoke(@PATH("id") id:String? = null


    companion object{
        const val BASE_URL ="http://api.icndb.com/jokes/"

        //random http://api.icndb.com/jokes/random
        private const val RANDOM_URL = "random"

        //change name http://api.icndb.com/jokes/random?firstName=John&lastName=Doe
        private const val CHANGE_NAME_URL="random?firstName=&lastName="

        //http://api.icndb.com/jokes/random?exclude=[explicit ]
        private const val EXCLUDE_EXPLICIT = "random?exclude="

        //http://api.icndb.com/jokes/random/10
        private const val RANDOM_COUNT_URL = "random/{number}"

        //specific joke url http://api.icndb.com/jokes/<id>
//        private const val ID_URL = "{id}"


    }
}