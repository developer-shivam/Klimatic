package app.klimatic.di.module

import app.klimatic.di.modules.dataManagerModule
import app.klimatic.di.modules.databaseModule
import app.klimatic.di.modules.viewModelModule

val testAppModules = listOf(
    testNetworkModule,
    databaseModule,
    dataManagerModule,
    viewModelModule
)
