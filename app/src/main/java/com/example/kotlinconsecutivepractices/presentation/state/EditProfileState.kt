package com.example.kotlinconsecutivepractices.presentation.state

import android.net.Uri

interface EditProfileState {
    val photoUri: Uri
    val name: String
    val resumeUrl: String
    val showSelect: Boolean
    val showPermission: Boolean
}