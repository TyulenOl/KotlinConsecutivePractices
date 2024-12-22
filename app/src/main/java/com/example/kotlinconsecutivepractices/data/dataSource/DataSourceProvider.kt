package com.example.kotlinconsecutivepractices.data.dataSource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.kotlinconsecutivepractices.data.serializer.ProfileSerializer
import com.example.kotlinconsecutivepractices.domain.model.ProfileEntity

class DataSourceProvider(
    private val context: Context
) {
    private val Context.profileDataStore: DataStore<ProfileEntity> by dataStore(
        fileName = "profile.pb",
        serializer = ProfileSerializer
    )

    fun provider(): DataStore<ProfileEntity> {
        return context.profileDataStore
    }
}