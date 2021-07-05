package app.klimatic.di.module

import app.klimatic.di.modules.databaseModule
import app.klimatic.di.modules.repositoryModule
import app.klimatic.di.modules.viewModelModule

val testAppModules = listOf(
    testNetworkModule,
    databaseModule,
    repositoryModule,
    viewModelModule
)
