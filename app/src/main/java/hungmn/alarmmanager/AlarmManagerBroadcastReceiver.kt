package hungmn.alarmmanager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.PowerManager
import android.util.Log
import android.widget.Toast
import java.text.Format
import java.text.SimpleDateFormat
import java.util.*

class AlarmManagerBroadcastReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val pm = context?.getSystemService(Context.POWER_SERVICE) as PowerManager
        val wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "wakelock:TAG")
        wl?.acquire(TIMEOUT)

        val message = Intent(context, AlarmManagerActivity::class.java)
        message.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(message)
        Log.i(TAG, "Start app!")

        wl?.release()
    }

    fun cancelAlarm(context: Context) {
        val intent = Intent(context, AlarmManagerBroadcastReceiver::class.java)
        val sender = PendingIntent.getBroadcast(context, 0, intent, 0)
        val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        am.cancel(sender)
    }

    companion object {
        const val TIMEOUT = 600000L
        const val TAG = "AlarmManager"
    }
}
