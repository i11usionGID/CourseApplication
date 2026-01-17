package com.i11usion.core.utils.formatter

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun String.toRuDate(): String {
    val date = LocalDate.parse(this)
    val formatter = DateTimeFormatter.ofPattern(
        "d MMMM yyyy",
        Locale("ru")
    )
    val formatted = date.format(formatter)
    return formatted.replaceFirstChar { it.uppercase() }
}
