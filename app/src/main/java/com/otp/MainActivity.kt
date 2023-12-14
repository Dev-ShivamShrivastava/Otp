package com.otp

import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.commonotpview.OtpView
import com.commonotpview.OtpViewType
import com.otp.ui.theme.OtpTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OtpTheme {
                    OtpView(type = OtpViewType.Round){

                    }
                }
            }
        }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
//        LocalBroadcastManager.getInstance(this).sendBroadcast(
//            Intent("key_up").apply {
//                putExtra("event", event)
//            }
//        )
        return super.onKeyUp(keyCode, event)
    }
}


