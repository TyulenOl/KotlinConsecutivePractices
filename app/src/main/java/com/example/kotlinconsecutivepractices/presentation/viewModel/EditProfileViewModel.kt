package com.example.kotlinconsecutivepractices.presentation.viewModel

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinconsecutivepractices.domain.repository.IProfileRepository
import com.example.kotlinconsecutivepractices.presentation.state.EditProfileState
import kotlinx.coroutines.launch

class EditProfileViewModel(
    private val profileRepository: IProfileRepository
) : ViewModel() {
    private val _editProfileState = MutableEditProfileState()
    val editProfileState = _editProfileState as EditProfileState

    init {
        viewModelScope.launch {
            profileRepository.getProfile()?.let {
                _editProfileState.name = it.name
                _editProfileState.resumeUrl = it.url
                _editProfileState.photoUri = Uri.parse(it.photoUri)
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

    fun saveParameters() {
        viewModelScope.launch {
            profileRepository.setProfile(
                editProfileState.photoUri.toString(),
                editProfileState.resumeUrl,
                editProfileState.name
            )
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

    private class MutableEditProfileState : EditProfileState {
        override var photoUri: Uri by mutableStateOf(Uri.EMPTY)
        override var name: String by mutableStateOf("")
        override var resumeUrl: String by mutableStateOf("")
        override var showSelect: Boolean by mutableStateOf(false)
        override var showPermission: Boolean by mutableStateOf(false)
    }
}