package hungmn.alarmmanager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInstaller
import android.util.Log

class InstallReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        when (val status = intent.getIntExtra(PackageInstaller.EXTRA_STATUS, -1)) {
            PackageInstaller.STATUS_PENDING_USER_ACTION -> {
                val confirmationIntent = intent.getParcelableExtra<Intent>(Intent.EXTRA_INTENT)
                Log.d(TAG, "onReceive ${confirmationIntent?.action ?: "unknown action"}")
                confirmationIntent?.let {
                    try {
                        context.startActivity(it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
                    } catch (e: Exception) {}
                }
            }
            PackageInstaller.STATUS_SUCCESS -> {
                Log.d(TAG, "Package install successfully")
            }
            else -> {
                val msg = intent.getStringExtra(PackageInstaller.EXTRA_STATUS_MESSAGE)
                Log.e(TAG, "Received $status and $msg")
            }
        }
    }

    companion object {
        const val TAG = "InstallReceiver"
    }
}
