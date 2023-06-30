package com.nuzhnov.testtask.presentation.widget

import com.nuzhnov.testtask.R
import com.nuzhnov.testtask.presentation.MainActivity
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

class CarsWidgetProvider : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        appWidgetIds.forEach { appWidgetId ->
            val pendingIntent = PendingIntent.getActivity(
                /* context = */ context,
                /* requestCode = */ 0,
                /* intent = */ Intent(context, MainActivity::class.java),
                /* flags = */ PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            val views = RemoteViews(context.packageName, R.layout.cars_list_widget)
        }
    }
}
