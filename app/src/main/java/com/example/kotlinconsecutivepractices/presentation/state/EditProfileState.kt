package com.example.kotlinconsecutivepractices.presentation.state

import android.net.Uri
import java.time.LocalTime

interface EditProfileState {
    val photoUri: Uri
    val name: String
    val resumeUrl: String
    val showSelect: Boolean
    val showPermission: Boolean
    val showTimePicker: Boolean
    val time: LocalTime
    val timeString: String
    val timeError: String?
}