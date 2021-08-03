package app.klimatic.ui.utils

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

fun Context?.hasPermissions(permissions: Array<String>) =
    this?.let {
        permissions.all {
            ActivityCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
        }
    } ?: run {
        false
    }