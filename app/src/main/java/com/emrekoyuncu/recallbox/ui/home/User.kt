package com.emrekoyuncu.recallbox.ui.home

import com.google.firebase.database.Exclude

data class User(val name: String? = null, val surname:String? = null, val gender:String? = null, val length:String? = null, val weight:String? = null) {

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "name" to name,
            "surname" to surname,
            "gender" to gender   ,
            "length" to length,
            "weight" to weight
        )
    }
}


