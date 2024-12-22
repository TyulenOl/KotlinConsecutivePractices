package com.example.kotlinconsecutivepractices.presentation.viewModel

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinconsecutivepractices.domain.repository.IProfileRepository
import com.example.kotlinconsecutivepractices.presentation.state.ProfileState
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val profileRepository: IProfileRepository
) : ViewModel() {
    private val _profileState = MutableProfileState()
    val profileState = _profileState as ProfileState

    init {
        viewModelScope.launch {
            profileRepository.observeProfile().collect {
                _profileState.name = it.name
                _profileState.photoUri = Uri.parse(it.photoUri)
                _profileState.resumeUrl = it.url
            }
        }
    }


    private class MutableProfileState : ProfileState {
        override var photoUri: Uri by mutableStateOf(Uri.EMPTY)
        override var name: String by mutableStateOf("")
        override var resumeUrl: String by mutableStateOf("")
    }
}