package ru.health.airly

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.defaultComponentContext
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import ru.health.airly.root.api.AppComponent
import ru.health.core.presentation.ui.theme.AirlyTheme

class MainActivity : ComponentActivity() {

    private lateinit var component: AppComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val application = application as AirlyApp
        application.mainDaggerComponent.inject(this)

        val daggerComponent = application.mainDaggerComponent
        component = daggerComponent.rootComponentFactory(defaultComponentContext())

        setContent {
            FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("TAG", "Fetching FCM registration token failed", task.exception)
                    return@OnCompleteListener
                }
                
                Log.e("TAG", task.result)
            })

            AirlyTheme {
                Box(modifier = Modifier.imePadding()) {
                    component.Render(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}