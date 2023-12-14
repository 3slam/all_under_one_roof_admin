package com.example.myapplication.di



import com.example.myapplication.data.repo.AuthenticationRepository
import com.example.myapplication.data.repo.AuthenticationRepositoryImp
import com.example.myapplication.data.repo.CustomerRepository
import com.example.myapplication.data.repo.CustomerRepositoryImpl
import com.example.myapplication.data.service.AuthenticationService
import com.example.myapplication.data.service.CustomerService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
 class RepositoryModule {

    @Provides
    fun provideAuthRepository(authenticationService: AuthenticationService): AuthenticationRepository {
        return  AuthenticationRepositoryImp(authenticationService)
    }

    @Provides
    fun provideCustomerRepository(customerService: CustomerService): CustomerRepository  {
        return  CustomerRepositoryImpl( customerService)
    }

}