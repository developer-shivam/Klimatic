package app.klimatic.ui.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import app.klimatic.ui.utils.Constants.EMPTY
import app.klimatic.ui.utils.Constants.SPACE
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun Context.toast(text: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, duration).show()
}

fun Context.toast(@StringRes text: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, duration).show()
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun String?.getHours(): String {
    return this?.split(SPACE)?.last() ?: EMPTY
}

fun String.toIntOrZero(): Int {
    return when (val number = this.toIntOrNull()) {
        null -> 0
        else -> number
    }
}

fun Context?.getCurrentHours(): Int {
    return SimpleDateFormat("HH", Locale.getDefault()).format(Calendar.getInstance().time)
        .toIntOrZero()
}
