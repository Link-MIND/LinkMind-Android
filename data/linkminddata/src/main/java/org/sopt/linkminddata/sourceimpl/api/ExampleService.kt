package org.sopt.linkminddata.sourceimpl.api

import org.sopt.linkminddata.model.remote.response.ResponseGetDummyDto
import retrofit2.http.GET

interface ExampleService {
  @GET("dummy")
  suspend fun getDummy(): ResponseGetDummyDto
}
