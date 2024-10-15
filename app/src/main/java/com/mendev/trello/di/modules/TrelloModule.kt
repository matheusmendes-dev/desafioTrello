package com.mendev.trello.di.modules

import com.mendev.trello.data.repository.TrelloRepositoryImpl
import com.mendev.trello.domain.repository.TrelloRepository
import com.mendev.trello.domain.usecase.TrelloUseCase
import com.mendev.trello.domain.usecase.TrelloUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class TrelloModule {

    @Binds
    abstract fun bindRepository(
        repositoryImpl: TrelloRepositoryImpl
    ): TrelloRepository

    @Binds
    abstract fun bindUseCase(
        useCaseImpl: TrelloUseCaseImpl
    ): TrelloUseCase

}