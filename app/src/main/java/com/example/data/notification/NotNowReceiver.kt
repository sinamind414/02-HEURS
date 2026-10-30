package com.example.data.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationManagerCompat

/**
 * §9 (slice 4): the easy exit. Every reminder carries a "Not now" action;
 * tapping it dismisses the notification and does nothing else — the alarm
 * chain keeps re-arming exactly as before, nothing is recorded, no snooze
 * is played, and the user is never asked to justify the tap.
 *
 * Not exported: only the app's own pending intents may trigger it.
 */
class NotNowReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action != DivineNotificationHelper.ACTION_NOT_NOW) return
        val notificationId = intent.getIntExtra(
            DivineNotificationHelper.EXTRA_NOT_NOW_NOTIFICATION_ID,
            Int.MIN_VALUE
        )
        if (notificationId == Int.MIN_VALUE) return
        try {
            NotificationManagerCompat.from(context).cancel(notificationId)
        } catch (_: SecurityException) {
            // Notifications permission revoked mid-flight: nothing to cancel.
        }
    }
}
