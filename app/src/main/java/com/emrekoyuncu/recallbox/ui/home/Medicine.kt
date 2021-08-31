package com.emrekoyuncu.recallbox.ui.home

import com.google.firebase.database.Exclude

data class Medicine(val medicine_name: String? = null, val medicine_day:String? = null, val medicine_hour:String? = null) {

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "medicine_name" to medicine_name,
            "medicine_day" to medicine_day,
            "medicine_hour" to medicine_hour
        )
    }
}
