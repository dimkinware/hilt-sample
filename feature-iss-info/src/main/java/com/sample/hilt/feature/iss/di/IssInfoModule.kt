package com.sample.hilt.feature.iss.di

import com.sample.hilt.feature.iss.api.IssInfoApi
import com.sample.hilt.feature.iss.repository.IssInfoRepository
import com.sample.hilt.feature.iss.repository.IssInfoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.ViewScoped
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class IssInfoModule {

    @ActivityRetainedScoped
    @Binds
    internal abstract fun bindIssInfoRepository(repository: IssInfoRepositoryImpl): IssInfoRepository

    internal companion object {
        @Provides
        fun provideIssInfoRestApi(retrofit: Retrofit.Builder) : IssInfoApi {
            return retrofit
                .baseUrl("http://api.open-notify.org")
                .build()
                .create(IssInfoApi::class.java)
        }
    }
}