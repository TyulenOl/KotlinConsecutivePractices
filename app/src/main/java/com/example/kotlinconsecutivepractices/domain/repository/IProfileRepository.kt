package com.example.kotlinconsecutivepractices.domain.repository

import com.example.kotlinconsecutivepractices.domain.model.ProfileEntity
import kotlinx.coroutines.flow.Flow

interface IProfileRepository {
    suspend fun getProfile(): ProfileEntity?
    suspend fun setProfile(photoUri: String, url: String, name: String): ProfileEntity
    suspend fun observeProfile(): Flow<ProfileEntity>
}