package com.agalobr.ex03views.features.ex04Lodging.data

import com.agalobr.ex03views.app.Either
import com.agalobr.ex03views.app.ErrorApp
import com.agalobr.ex03views.features.ex04Lodging.data.local.XmlLodgingLocalDataSource
import com.agalobr.ex03views.features.ex04Lodging.data.remote.api.LodgingApiClient
import com.agalobr.ex03views.features.ex04Lodging.domain.Lodging
import com.agalobr.ex03views.features.ex04Lodging.domain.LodgingRepository

class DataSourceRepository(
    private val localDataSource: XmlLodgingLocalDataSource,
    private val remoteDataSource: LodgingApiClient
) : LodgingRepository {
    override suspend fun getLodging(): Either<ErrorApp, Lodging> {
        val list = localDataSource.getLodging()
        list.mapLeft {
            remoteDataSource.getLodging().map { lodging ->
                localDataSource.saveLodging(lodging)
                lodging
            }
        }
        return list
    }
}