package com.shafayat.helloworldkm.android.di

import android.content.Context
import com.shafayat.helloworldkm.data.local.DatabaseDriverFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    fun provideDatabaseDriverFactory(@ApplicationContext context: Context): DatabaseDriverFactory {
        return DatabaseDriverFactory(context)
    }
}