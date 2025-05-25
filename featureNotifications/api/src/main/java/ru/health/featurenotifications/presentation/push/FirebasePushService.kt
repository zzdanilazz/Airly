package ru.health.featurenotifications.presentation.push

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class FirebasePushService : FirebaseMessagingService() {

//    @Inject
//    lateinit var notificationProcess: NotificationProcess
//
//    @field:Inject
//    lateinit var saveTokenUseCase: SaveTokenUseCase
//
//    @field:Inject
//    lateinit var collectPushUseCase: CollectPushUseCase
//
//    @field:Inject
//    lateinit var dateFormatter: DateFormatter
//
//    @field:Inject
//    lateinit var moshi: Moshi

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    override fun onCreate() {
        super.onCreate()
//        (application as PushServiceInjectAssistant).inject(this)
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

//            val payload = message.data[Payload.PAYLOAD_KEY]?.toPayload(moshi)
//
//            val push = PushEntity(
//                pushId = Date().time.toInt(),
//                pushType = payload?.contentName?.toNotificationType() ?: NotificationType.MAIN,
//                itemId = payload?.vehicleId,
//                title = title,
//                message = body,
//                date = dateFormatter.formatDate(Date(), DateFormatter.FULL_DATE_FORMAT)
//            )
//
//            if (AppLifecycleObserver.isAppInForeground) {
//                collectPushUseCase(push)
//            } else {
//                val deepLink = DeepLinkBuilder()
//                    .setContentName(push.pushType.screenUri)
//                    .setItemId(push.itemId)
//                    .build()
//
//                notificationProcess.createNotification(push.title, push.message, deepLink)
//            }
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.e("TAG", token)
//        scope.launch {
//            saveTokenUseCase(token)
//        }
    }
}
