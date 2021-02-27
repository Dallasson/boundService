package com.dz.boundservice

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import com.dz.boundservice.services.LocalService

class MainActivity : AppCompatActivity() {
    private lateinit var localService: LocalService
    private lateinit var serviceConnection: ServiceConnection
    private  var isBound = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // TODO :
         // FOREGROUND : start + stop
        // BACKGROUND : onCreate + ondestroy


         serviceConnection  = object : ServiceConnection{
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                val localBinder  = service as  LocalService.LocalBinder
                localService = localBinder.getLocaleService()

                isBound = true
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                isBound = false
            }

        }
         executeSomeCode()
    }

    override fun onStart() {
        super.onStart()
        Intent(this,LocalService::class.java).apply {
            bindService(this,serviceConnection, Context.BIND_AUTO_CREATE)
        }
    }
    override fun onStop() {
        super.onStop()
        if(isBound && localService != null){
            unbindService(serviceConnection)
        }

    }
    private fun executeSomeCode(){
        if(isBound && localService != null){
            localService.getDisplayedName()
        }
    }
}