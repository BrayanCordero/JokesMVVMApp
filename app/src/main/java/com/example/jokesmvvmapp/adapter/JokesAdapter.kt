package com.example.jokesmvvmapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jokesmvvmapp.databinding.JokeItemBinding
import com.example.jokesmvvmapp.models.Joke


class JokesAdapter(
    private val jokeSet : MutableList<Joke> = mutableListOf(),
    private val onJokeClicked:(String?)->Unit

): RecyclerView.Adapter<JokesViewHolder>() {

    fun updateNewJokes(newJokes: List<Joke>){
//        jokeSet.clear()
        jokeSet.addAll(newJokes)
        notifyDataSetChanged()
    }

    fun clearJokes(){
        jokeSet.clear()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokesViewHolder =
        JokesViewHolder(
            JokeItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: JokesViewHolder, position: Int) =
        holder.bind(jokeSet[position], onJokeClicked)

    override fun getItemCount(): Int = jokeSet.size
}

class JokesViewHolder(
    private val binding : JokeItemBinding
): RecyclerView.ViewHolder(binding.root){
    fun bind(joke:Joke, onJokeClicked: (String?) -> Unit){
        binding.jokeId.text = joke.id.toString()
        binding.jokeText.text = joke.joke

        //this will pass the id
        itemView.setOnClickListener { onJokeClicked(joke.id.toString()) }

    }

}