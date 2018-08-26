package com.mdelacruz.imagefindermvp.util

import android.content.Context
import android.widget.Toast

fun Context.toast(message: Int) {
  val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
  toast.show()
}
