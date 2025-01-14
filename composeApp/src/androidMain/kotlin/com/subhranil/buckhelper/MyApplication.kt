package com.subhranil.buckhelper

import android.app.Application
import com.subhranil.buckhelper.di.initializeKoin

class MyApplication: Application() {
   override fun onCreate() {
       super.onCreate()
       initializeKoin()
   }
}