package com.xonicsports_sportnews.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import com.xonicsports_sportnews.MainXonicsActivity
import com.xonicsports_sportnews.R

object Coroutines : android.app.Application() {
    @SuppressLint("ResourceType")
    fun <T : Any> ioThenMain(work: suspend (() -> T?), callback: ((T?) -> Unit), context: Context) =
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val data = CoroutineScope(Dispatchers.IO).async rt@{ return@rt work() }.await()
                callback(data)
            }catch (e: NoInternetException){

                    val alertDialog: AlertDialog = context.let {
                    val builder = AlertDialog.Builder(it)
                    builder.setTitle("No active internet connection")
                    builder.setMessage("Check DATA or WIFI connection")
                    builder.apply {
                        setPositiveButton("RELOAD APP",
                            DialogInterface.OnClickListener { dialog, id ->
                                val intent = Intent(context, MainXonicsActivity::class.java)
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                context.startActivity(intent)
                                Runtime.getRuntime().exit(0)
                            })
                        setNegativeButton(
                            R.string.cancel,
                            DialogInterface.OnClickListener { dialog, id ->
                                // User cancelled the dialog
                            })
                    }
                    builder.create()
                }
                alertDialog.show()

            }
        }
}
