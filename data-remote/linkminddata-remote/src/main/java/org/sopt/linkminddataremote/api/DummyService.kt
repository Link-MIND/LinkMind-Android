package org.sopt.linkminddataremote.source.api

import org.sopt.linkminddata.model.remote.response.ResponseGetDummyDto
import retrofit2.http.GET

interface DummyService {
  @GET("dummy")
  suspend fun getDummy(): ResponseGetDummyDto
}
