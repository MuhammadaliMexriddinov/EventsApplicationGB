package uz.gita.eventsapplicationgb.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.MediaPlayer
import android.net.Uri
import android.net.wifi.WifiManager
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews
import android.widget.Toast
import androidx.core.app.NotificationCompat
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.eventsapplicationgb.MainActivity
import uz.gita.eventsapplicationgb.R
import uz.gita.eventsapplicationgb.broadcast.EventBroadcast
import java.io.File


@AndroidEntryPoint
class EventService : Service() {

    override fun onBind(intent: Intent?): IBinder? = null

    private var eventBroadcast: EventBroadcast? = null

    override fun onCreate() {
        super.onCreate()

        if (eventBroadcast == null) {
            eventBroadcast = EventBroadcast()
        }

        createChannel()
        startMyService()

//        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        notificationManager.notify("tag", id, notification)

        registerReceiver(eventBroadcast, IntentFilter().apply {
            addAction(Intent.ACTION_SCREEN_ON)
            addAction(Intent.ACTION_SCREEN_OFF)
            addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION)
            addAction(BluetoothAdapter.ACTION_STATE_CHANGED)
            addAction(Intent.ACTION_HEADSET_PLUG)
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            addAction(Intent.ACTION_TIME_CHANGED)
            addAction(Intent.ACTION_SHUTDOWN)
            addAction(Intent.ACTION_BATTERY_LOW)
            addAction(Intent.ACTION_BATTERY_OKAY)
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
            addAction(Intent.ACTION_REBOOT)
        }
        )
    }

    private  fun  startMyService(){
        val id = 123
        val notification = NotificationCompat.Builder(this, "Event").apply {
            setSmallIcon(R.drawable.main_ic)
            setContentTitle("Events Detector")
            setCustomContentView(createRemoteView())
            setContentText("This app listen events from System")
            setStyle(NotificationCompat.DecoratedCustomViewStyle())
            setContentIntent(PendingIntent.getActivity(this@EventService, 0, Intent(this@EventService, MainActivity::class.java), PendingIntent.FLAG_IMMUTABLE))
        }.build()


        startForeground(id, notification)
    }


    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                "Event",
                "Main",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager: NotificationManager =
                getSystemService(NotificationManager::class.java)

            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    private fun createRemoteView(): RemoteViews {
        val view = RemoteViews(this.packageName, R.layout.remote_view)
        view.setTextViewText(R.id.textMusicName, "This app listen events from System")
        view.setTextViewText(R.id.textArtistName, "The Events Detector app is running ")
        view.setOnClickPendingIntent(R.id.buttonCancel, createPendingIntent(ActionEnum.CANCEL))
        return view
    }

    private fun createPendingIntent(action: ActionEnum): PendingIntent {
        val intent = Intent(this, EventService::class.java)
        intent.putExtra("COMMAND", action)
        return PendingIntent.getService(this, action.pos, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//           Log.d("TTTT", "update")
//        //startMyService()


          intent?.extras?.let {
             val command=it.getSerializable("COMMAND") as ActionEnum
              if (command == ActionEnum.CANCEL) stopSelf()
          }
        //doneCommand(command)
        //  Log.d("BBB", "${intent.toString()}")
        return START_NOT_STICKY
    }
}

enum class ActionEnum(val pos: Int) {
    CANCEL(0)
}
