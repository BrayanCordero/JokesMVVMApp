package com.example.jokesmvvmapp.network

import com.example.jokesmvvmapp.models.Joke
import com.example.jokesmvvmapp.models.JokesResponse
import com.example.jokesmvvmapp.models.RandomJoke
import retrofit2.Response
import javax.inject.Inject

//2)

interface JokesRepository {
   suspend fun getAllJokes():Response<JokesResponse>
   suspend fun changeName(firstName:String, lastName:String?):Response<JokesResponse>
   suspend fun getRandomJoke():Response<RandomJoke>
}

class JokesRepositoryImpl @Inject constructor(
    private val jokesService: JokesService
): JokesRepository
{
    override suspend fun getAllJokes():Response<JokesResponse> =
        jokesService.getAllJokes()


    override suspend fun changeName(firstName:String, lastName:String?):Response<JokesResponse> =
        jokesService.changeName(firstName,lastName)

    override suspend fun getRandomJoke(): Response<RandomJoke> =
        jokesService.getRandomJoke()



}