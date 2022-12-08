package hungmn.alarmmanager

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast

class AlarmManagerActivity : Activity() {

    private var alarm: AlarmManagerBroadcastReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm_manager)
        alarm = AlarmManagerBroadcastReceiver()
    }

    fun cancelRepeatingTimer(view: View) {
        val context = this.applicationContext
        if (alarm != null) {
            alarm?.cancelAlarm(context)
        } else {
            Toast.makeText(context, "Alarm is null", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_widget_alarm_manager, menu)
        return true
    }
}
