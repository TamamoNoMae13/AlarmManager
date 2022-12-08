package hungmn.alarmmanager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import java.util.*

/**
 *
 */
class RevivalReceiver : BroadcastReceiver() {

    private var x: Long? = null

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        if (action == Intent.ACTION_BOOT_COMPLETED || action == Intent.ACTION_MY_PACKAGE_REPLACED) {
            Toast.makeText(context, "Start app in designated time and every 15 minutes", Toast.LENGTH_SHORT).show()

            val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent1 = Intent(context, AlarmManagerBroadcastReceiver::class.java)
            val pi = PendingIntent.getBroadcast(context, 0, intent1, 0)

            val calendar = Calendar.getInstance() // get current
            calendar.set(2022, 11, 5, 18, 3, 0)
            x = calendar.timeInMillis

//            x = System.currentTimeMillis() + 15000L
            Log.i(TAG, "Log:\nSystem  : ${System.currentTimeMillis()}\nCalendar: $x")

            am.setRepeating(AlarmManager.RTC_WAKEUP, x!!, INTERVAL, pi)
        }
    }

    companion object {
        const val TAG = "RevivalReceiver"
        const val INTERVAL = AlarmManager.INTERVAL_FIFTEEN_MINUTES
    }
}
