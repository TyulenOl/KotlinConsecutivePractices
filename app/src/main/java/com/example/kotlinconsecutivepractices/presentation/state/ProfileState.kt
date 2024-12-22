package com.example.kotlinconsecutivepractices.presentation.state

import android.net.Uri

interface ProfileState {
    val photoUri: Uri
    val name: String
    val resumeUrl: String
}