package com.example.demo2.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class BroadCastReceivers : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val isAirPlaneModeEnabled= intent?.getBooleanExtra("state",false)?: return
        if(isAirPlaneModeEnabled){
            Toast.makeText(context,"Airplane mode Enabled",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context,"Airplane mode Disabled",Toast.LENGTH_SHORT).show()
        }
    }
}