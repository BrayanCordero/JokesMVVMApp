package com.example.jokesmvvmapp.DI

import com.example.jokesmvvmapp.network.JokesRepository
import com.example.jokesmvvmapp.network.JokesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


//6)

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providesJokesRepository(
        jokesRepositoryImpl: JokesRepositoryImpl
    ): JokesRepository
}