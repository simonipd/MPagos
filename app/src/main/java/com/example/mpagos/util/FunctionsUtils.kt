package com.example.mpagos.util

import android.app.Activity
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.example.mpagos.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FunctionsUtils {
    companion object{

        fun <T> LifecycleOwner.launchAndCollect(
            flow: Flow<T>,
            state: Lifecycle.State = Lifecycle.State.STARTED,
            body: (T) -> Unit
        ) {
            lifecycleScope.launch {
                this@launchAndCollect.repeatOnLifecycle(state) {
                    flow.collect(body)
                }
            }
        }

        fun toastDefault(msj: String, activity: Activity){
            Toast.makeText(activity.baseContext, msj, Toast.LENGTH_SHORT).show()
        }

        fun ImageView.loadUrl(url: String){
            Glide.with(this)
                .load(url)
                .error(R.drawable.gif_loader)
                .fallback(R.drawable.gif_loader)
                .into(this)
        }
    }
}