package com.example.android.sunshine.app.widget;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.RemoteViews;

import com.example.android.sunshine.app.MainActivity;
import com.example.android.sunshine.app.R;
import com.example.android.sunshine.app.Utility;

/**
 * Created by Owen LaRosa on 9/3/16.
 */

public class TodayWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        int weatherArtResourceId = R.drawable.art_clear;
        String description = "Clear";
        double maxTemp = 24;
        String formattedMaxTemperature = Utility.formatTemperature(context, maxTemp);

        for (int appWidgetId : appWidgetIds) {
            int layout_id = R.layout.widget_today_small;
            RemoteViews views = new RemoteViews(context.getPackageName(), layout_id);

            views.setImageViewResource(R.id.widget_icon, weatherArtResourceId);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
                setRemoteContentDescription(views, description);
            }

            views.setTextViewText(R.id.widget_high_temperature, formattedMaxTemperature);

            Intent launchIntent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, launchIntent, 0);
            views.setOnClickPendingIntent(R.id.widget, pendingIntent);

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    private void setRemoteContentDescription(RemoteViews views, String description) {
        views.setContentDescription(R.id.widget_icon, description);
    }

}
