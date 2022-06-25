package com.example.jokesmvvmapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//7) add to manifest

@HiltAndroidApp
class JokesApp : Application(){
}