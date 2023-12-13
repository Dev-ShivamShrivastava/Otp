package com.commonotpview

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import android.view.KeyEvent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.localbroadcastmanager.content.LocalBroadcastManager

@Composable
fun OtpView(
    size: Int = 4,
    customModifier: Modifier? = null,
    type: OtpViewType = OtpViewType.Round,
    otp: (String) -> Unit
) {
    val modifier: Modifier = customModifier ?: Modifier
        .width(50.dp)
        .background(color = Color.Transparent)
    val otpNumber = Array<String>(size) { "0" }
    var nextTempValue = ""
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    DisposableEffect(Unit) {
        val lbm = LocalBroadcastManager.getInstance(context)
        // Receiver for key events
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(ctx: Context?, intent: Intent?) {
                intent?.getParcelableExtra<KeyEvent>("event")?.let { keyEvent ->
                    when (keyEvent.keyCode) {
                        KeyEvent.KEYCODE_DEL -> {
                            focusManager.moveFocus(
                                focusDirection = FocusDirection.Left,
                            )
                        }
                        else -> {

                        }
                    }
                }
            }
        }
        // registering the receiver
        lbm.registerReceiver(receiver, IntentFilter("key_up"))
        onDispose {
            // unregistering when the composable is disposed
            lbm.unregisterReceiver(receiver)
        }
    }
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        items(size) { item ->
            val text = remember { mutableStateOf("") }
            val focusRequester = remember {
                FocusRequester()
            }


            Box(
                modifier = modifier,
                contentAlignment = Alignment.Center
            ) {
                when (type) {
                    OtpViewType.Circle -> {
                        LaunchedEffect(key1 = Unit) {
                            if (0 == item) {
                                focusRequester.requestFocus()
                            }
                        }

                        TextField(
                            value = text.value,
                            onValueChange = { newText ->
                                if (newText.length <= 1) {
                                    text.value = newText
                                    otpNumber[item] = newText
                                    // Checking condition complete otp fill or not
                                    if (item == (size - 1) && otpNumber[item].isNotEmpty()) {
                                        var otpStr = ""
                                        otpNumber.forEach {
                                            otpStr += "$it"
                                        }
                                        otp(otpStr)
                                    }

                                    if (item != (size - 1)) {
                                        if (text.value.isNotEmpty()) {
                                            focusManager.moveFocus(
                                                focusDirection = FocusDirection.Next,
                                            )
                                        }
                                    }
                                } else {
                                    if (text.value.isNotEmpty() && item != (size - 1)) {
//
                                        focusManager.moveFocus(
                                            focusDirection = FocusDirection.Next,
                                        )
                                        text.value = newText

                                    }

                                }
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number,
                            ),
                            modifier = Modifier
                                .focusRequester(focusRequester)
                                .border(1.dp, color = Color.Black, shape = CircleShape)
                                .background(color = Color.Transparent),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                disabledContainerColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                            ),
                            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                        )
                    }

                    OtpViewType.Line -> {
                        LaunchedEffect(key1 = Unit) {
                            if (0 == item) {
                                focusRequester.requestFocus()
                            }
                        }
                        TextField(
                            value = text.value,
                            onValueChange = { newText ->
                                if (newText.length <= 1) {
                                    text.value = newText
                                    otpNumber[item] = newText
                                    // Checking condition complete otp fill or not
                                    if (item == (size - 1) && otpNumber[item].isNotEmpty()) {
                                        var otpStr = ""
                                        otpNumber.forEach {
                                            otpStr += "$it"
                                        }
                                        otp(otpStr)
                                    }

                                    if (item != (size - 1)) {
                                        if (text.value.isNotEmpty()) {
                                            focusManager.moveFocus(
                                                focusDirection = FocusDirection.Next,
                                            )
                                        }
                                    }
                                } else {
                                    if (text.value.isNotEmpty() && item != (size - 1)) {
//
                                        focusManager.moveFocus(
                                            focusDirection = FocusDirection.Next,
                                        )
                                    }

                                }
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number,
                            ),
                            modifier = Modifier
                                .focusRequester(focusRequester)
                                .background(
                                    color = Color.Transparent
                                ),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                disabledContainerColor = Color.Transparent,
                            ),
                            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                        )
                    }

                    OtpViewType.Round -> {
                        LaunchedEffect(key1 = Unit) {
                            if (0 == item) {
                                focusRequester.requestFocus()
                            }
                        }
                        OutlinedTextField(
                            value = text.value,
                            onValueChange = { newText ->
                                if (newText.length <= 1) {
                                    text.value = newText
                                    otpNumber[item] = newText

                                    // Checking condition complete otp fill or not
                                    if (item == (size - 1) && otpNumber[item].isNotEmpty()) {
                                        var otpStr = ""
                                        otpNumber.forEach {
                                            otpStr += "$it"
                                        }
                                        otp(otpStr)
                                    }

                                    if (item != (size - 1)) {
                                        if (text.value.isNotEmpty()) {
                                            focusManager.moveFocus(
                                                focusDirection = FocusDirection.Next,
                                            )
                                        }
                                    }
                                } else {
                                    if (text.value.isNotEmpty() && item != (size - 1)) {
                                        focusManager.moveFocus(
                                            focusDirection = FocusDirection.Next,
                                        )
                                    }
                                }
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number,
                            ),
                            modifier = Modifier.focusRequester(focusRequester),
                            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                        )
                    }
                }

            }
        }
    }

}

fun Context.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}