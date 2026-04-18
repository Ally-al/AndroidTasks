package com.example.androidtasks.FourthModule

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.Worker
import androidx.work.WorkerParameters


class ChargingWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {
    override fun doWork(): Result {
        showNotification()
        return Result.success()
    }

    private fun showNotification() {

        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager

        val channelId = "charging_channel"
        val channel = NotificationChannel(
            channelId,
            "Charging Notifications",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(channel)

        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setContentTitle("Зарядка подключена")
            .setContentText("Устройство находится на зарядке")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .build()

        notificationManager.notify(1, notification)
    }
}

// вызов, например в Activity
val constraints = Constraints.Builder()
    .setRequiresCharging(true)
    .build()

val workRequest = OneTimeWorkRequestBuilder<ChargingWorker>()
    .setConstraints(constraints)
    .build()

WorkManager.getInstance(this)
    .enqueue(workRequest)

