package com.otp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.commonotpview.OtpView
import com.commonotpview.OtpViewType
import com.otp.ui.theme.OtpTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OtpTheme {
                    OtpView(type = OtpViewType.Circle){

                    }
                }
            }
        }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        LocalBroadcastManager.getInstance(this).sendBroadcast(
            Intent("key_up").apply {
                putExtra("event", event)
            }
        )
        return super.onKeyUp(keyCode, event)
    }
}


