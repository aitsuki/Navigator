package com.aitsuki.navigator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aitsuki.navigator.annotation.Argument
import com.aitsuki.navigator.annotation.Navigator

@Navigator(
    args = [
        Argument(argType = "long", name = "userId", defaultValue = "0L"),
        Argument(argType = "string", name = "username", defaultValue = "")
    ]
)
class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }
}
