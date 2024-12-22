package com.example.kotlinconsecutivepractices.domain.repository

import com.example.kotlinconsecutivepractices.domain.model.ProfileEntity
import kotlinx.coroutines.flow.Flow
import java.time.LocalTime

interface IProfileRepository {
    suspend fun getProfile(): ProfileEntity?
    suspend fun setProfile(photoUri: String, url: String, name: String, time: LocalTime): ProfileEntity
    suspend fun observeProfile(): Flow<ProfileEntity>
}