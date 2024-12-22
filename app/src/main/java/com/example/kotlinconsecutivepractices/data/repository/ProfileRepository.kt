package com.example.kotlinconsecutivepractices.data.repository

import androidx.datastore.core.DataStore
import com.example.kotlinconsecutivepractices.domain.model.ProfileEntity
import com.example.kotlinconsecutivepractices.domain.repository.IProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import org.koin.core.qualifier.named
import org.koin.java.KoinJavaComponent.inject

class ProfileRepository : IProfileRepository {
    private val dataStore: DataStore<ProfileEntity> by inject(
        DataStore::class.java,
        named("profile")
    )

    override suspend fun getProfile(): ProfileEntity? {
        return dataStore.data.firstOrNull()
    }

    override suspend fun setProfile(photoUri: String, url: String, name: String): ProfileEntity {
        return dataStore.updateData {
            it.toBuilder().apply {
                this.photoUri = photoUri
                this.url = url
                this.name = name
            }.build()
        }
    }

    override suspend fun observeProfile(): Flow<ProfileEntity> {
        return dataStore.data
    }
}