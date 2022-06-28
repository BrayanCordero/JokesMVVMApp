package com.example.jokesmvvmapp.network

import com.example.jokesmvvmapp.models.Joke
import com.example.jokesmvvmapp.models.JokesResponse
import com.example.jokesmvvmapp.models.RandomJoke
import retrofit2.Response
import javax.inject.Inject

//2)

interface JokesRepository {
   suspend fun getAllJokes():Response<JokesResponse>
   suspend fun changeName(firstName:String, lastName:String?):Response<RandomJoke>
   suspend fun getRandomJoke():Response<RandomJoke>
   suspend fun get20Jokes(number : Int): Response<JokesResponse>
}

class JokesRepositoryImpl @Inject constructor(
    private val jokesService: JokesService
): JokesRepository
{
    override suspend fun getAllJokes():Response<JokesResponse> =
        jokesService.getAllJokes()


    override suspend fun changeName(firstName:String, lastName:String?):Response<RandomJoke> =
        jokesService.changeName(firstName,lastName)

    override suspend fun getRandomJoke(): Response<RandomJoke> =
        jokesService.getRandomJoke()

    override suspend fun get20Jokes(number: Int): Response<JokesResponse> =
        jokesService.get20Jokes(number)



}