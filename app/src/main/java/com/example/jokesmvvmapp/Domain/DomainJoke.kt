package com.example.jokesmvvmapp.Domain

import com.example.jokesmvvmapp.models.Joke

data class DomainJoke(

    val id: Int,
    val joke: String,
    val category: List<String?>?

)

fun List<Joke>.mapToDomainJoke(): List<DomainJoke>{
    return this.map { networkJoke ->
        DomainJoke(
            id = networkJoke.id ?: 9999,
            joke = networkJoke.joke ?: "NOT AVAILABLE",
            category = networkJoke.categories
        )
    }
}
