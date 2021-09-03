package com.example.demo.services

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import android.R
import androidx.core.app.NotificationCompat
import android.app.NotificationManager
import android.app.NotificationChannel
import android.os.Build
import androidx.work.Data
import com.example.demo.constants.Constants


class NotificationWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {
        val taskData: Data = inputData
        val taskDataString = taskData.getString(Constants.MESSAGE_STATUS)
        showNotification("Demo", taskDataString ?: "Message has been Sent")
        val outputData: Data = Data.Builder().putString(WORK_RESULT, "Jobs Finished").build()
        return Result.success(outputData)
    }

    private fun showNotification(task: String, desc: String) {
        val manager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "login_success_message"
        val channelName = "success"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(applicationContext, channelId)
                .setContentTitle(task)
                .setContentText(desc)
                .setSmallIcon(R.mipmap.sym_def_app_icon)
        manager.notify(1, builder.build())
    }

    companion object {
        private const val WORK_RESULT = "work_result"
    }
}