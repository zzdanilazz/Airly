package ru.health.featurenotifications.impl.presentation.push

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ru.health.core.api.data.date.DateFormatter
import ru.health.core.impl.AppLifecycleObserver
import ru.health.featurenotifications.api.domain.PushEntity
import ru.health.featurenotifications.api.domain.SaveTokenUseCase
import ru.health.featurenotifications.api.presentation.NotificationProcess
import ru.health.featurenotifications.impl.presentation.di.PushServiceInjectAssistant
import java.util.Date
import javax.inject.Inject

class FirebasePushService : FirebaseMessagingService() {

    @Inject
    lateinit var notificationProcess: NotificationProcess

    @field:Inject
    lateinit var saveTokenUseCase: SaveTokenUseCase

    @field:Inject
    lateinit var dateFormatter: DateFormatter

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    override fun onCreate() {
        super.onCreate()
        (application as PushServiceInjectAssistant).inject(this)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        scope.launch {
            var title = message.data["title"] ?: ""
            var body = message.data["body"] ?: ""

            Log.d("TAG", "messageReceived $title")

            if (title.isBlank()) {
                title = message.data["gcm.notification.title"] ?: message.notification?.title ?: ""
                body = message.data["gcm.notification.body"] ?: message.notification?.body ?: ""
            }

            val push = PushEntity(
                pushId = Date().time.toInt(),
                title = title,
                message = body,
                date = dateFormatter.formatDate(Date(), DateFormatter.FULL_DATE_FORMAT)
            )

            if (!AppLifecycleObserver.isAppInForeground) {
                notificationProcess.createNotification(push.title, push.message)
            }
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        scope.launch {
            saveTokenUseCase(token)
        }
    }
}
