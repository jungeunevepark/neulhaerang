package com.finale.neulhaerang.data.api

import com.finale.neulhaerang.data.model.request.LoginReqDto
import com.finale.neulhaerang.data.model.response.LoginResDto
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.POST
import java.lang.reflect.Type
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


/**
 * retrofit2를 사용한 API 서비스
 * companion object (static)의 instance 사용(싱글톤)
 */
interface APIs {
    // auth 관련 함수
    @POST("auth/check")
    suspend fun postCheck(): String

    @POST("auth/login")
    suspend fun login(@Body loginReqDto: LoginReqDto): LoginResDto

    // member 관련 함수
    // ar 관련 함수
    // todo 관련 함수
    // title 관련 함수
    // item 관련 함수
    // routine 관련 함수

    companion object {
        private const val BASE_URL = "http://k9a502.p.ssafy.io:8080/"
//        private val gson: Gson = GsonBuilder().setLenient().create()
        private val gson: Gson = GsonBuilder().setLenient().registerTypeAdapter(LocalDateTime::class.java, GsonDateFormatAdapter()).create()
        private fun create(): APIs {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            return retrofit.create()
        }

        // 처음 instance 사용할 때 초기화
        val instance by lazy { create() }

    }
    class GsonDateFormatAdapter : JsonSerializer<LocalDateTime?>, JsonDeserializer<LocalDateTime?> {
        @Synchronized
        override fun serialize(localDateTime: LocalDateTime?, type: Type?, jsonSerializationContext: JsonSerializationContext?): JsonElement {
//            return JsonPrimitive(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS").format(localDateTime))
            return JsonPrimitive(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(localDateTime))
        }
        @Synchronized
        override fun deserialize(jsonElement: JsonElement, type: Type?, jsonDeserializationContext: JsonDeserializationContext?): LocalDateTime {
//            return LocalDateTime.parse(jsonElement.asString, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            return LocalDateTime.parse(jsonElement.asString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
        }
    }
}
