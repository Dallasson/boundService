package com.dz.boundservice.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import kotlin.random.Random

class LocalService :  Service() {

    private val iBinder = LocalBinder()
    override fun onBind(intent: Intent?): IBinder = iBinder

    class LocalBinder : Binder(){
        fun getLocaleService() : LocalService{
            return LocalService()
        }
    }
    fun getDisplayedName() : String {
        return "My Name is Taki Eddine"
    }
}