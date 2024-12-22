package com.example.kotlinconsecutivepractices.presentation.viewModel

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinconsecutivepractices.domain.repository.IProfileRepository
import com.example.kotlinconsecutivepractices.presentation.state.EditProfileState
import com.example.kotlinconsecutivepractices.presentation.utils.NotificationsReceiver
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class EditProfileViewModel(
    private val profileRepository: IProfileRepository,
    private val context: Context
) : ViewModel() {
    private val _editProfileState = MutableEditProfileState()
    val editProfileState = _editProfileState as EditProfileState

    private val formatter = DateTimeFormatter.ofPattern("HH:mm")

    init {
        viewModelScope.launch {
            profileRepository.getProfile()?.let {
                _editProfileState.name = it.name
                _editProfileState.resumeUrl = it.url
                _editProfileState.photoUri = Uri.parse(it.photoUri)
                try {
                    _editProfileState.time = LocalTime.parse(it.time, formatter)
                    updateTimeString()
                } catch (_: Exception) {
                }
            }
        }
        _editProfileState.showPermission = true
    }

    fun setName(name: String) {
        _editProfileState.name = name
    }

    fun setUrl(url: String) {
        _editProfileState.resumeUrl = url
    }

    fun onImageSelected(uri: Uri?) {
        uri?.let { _editProfileState.photoUri = it }
    }

    fun saveParameters(onBackNavigation: () -> Unit) {
        validateTime()
        if (_editProfileState.timeError != null) return
        viewModelScope.launch {
            profileRepository.setProfile(
                _editProfileState.photoUri.toString(),
                editProfileState.resumeUrl,
                editProfileState.name,
                editProfileState.time
            )
            onBackNavigation()
            saveNotification()
        }
    }

    fun onIconClick() {
        _editProfileState.showSelect = true
    }

    fun onPermissionClosed() {
        _editProfileState.showPermission = false
    }

    fun onSelectDismiss() {
        _editProfileState.showSelect = false
    }

    fun onTimeChanged(time: String) {
        _editProfileState.timeString = time
        validateTime()
    }

    fun onTimeInputClicked() {
        _editProfileState.showTimePicker = true
    }

    fun onTimeConfirmed(h: Int, m: Int) {
        _editProfileState.time = LocalTime.of(h, m)
        _editProfileState.timeError = null
        updateTimeString()
        onTimeDialogDismiss()
    }

    fun onTimeDialogDismiss() {
        _editProfileState.showTimePicker = false
    }

    private fun validateTime() {
        try {
            _editProfileState.time = LocalTime.parse(_editProfileState.timeString, formatter)
            _editProfileState.timeError = null
        } catch (e: Exception) {
            _editProfileState.timeError = "Некорректный формат времени"
        }
    }

    private fun updateTimeString() {
        _editProfileState.timeString = formatter.format(editProfileState.time)
    }

    private fun saveNotification() {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val dateTime = LocalDateTime.of(LocalDate.now(), editProfileState.time)
        val timeInMillis = dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()

        val notifyIntent = Intent(context, NotificationsReceiver::class.java)

        notifyIntent.putExtras(
            Bundle().apply {
                putString("NOTIFICATION", "Пора на пару, ${editProfileState.name}!")
            }
        )

        val notifyPendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            notifyIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        try {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                timeInMillis,
                notifyPendingIntent
            )
        } catch (e: SecurityException) {
            Log.d("alarmManager", "Failed to set reminder")
        }
    }

    private class MutableEditProfileState : EditProfileState {
        override var photoUri: Uri by mutableStateOf(Uri.EMPTY)
        override var name: String by mutableStateOf("")
        override var resumeUrl: String by mutableStateOf("")
        override var showSelect: Boolean by mutableStateOf(false)
        override var showPermission: Boolean by mutableStateOf(false)
        override var showTimePicker: Boolean by mutableStateOf(false)
        override var time: LocalTime by mutableStateOf(LocalTime.now())
        override var timeString: String by mutableStateOf("")
        override var timeError: String? by mutableStateOf(null)
    }
}