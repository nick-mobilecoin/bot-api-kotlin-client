package one.mixin.bot.api.call

import com.google.gson.JsonObject
import one.mixin.bot.api.MixinResponse
import one.mixin.bot.vo.Account
import one.mixin.bot.vo.AccountRequest
import one.mixin.bot.vo.GhostKey
import one.mixin.bot.vo.GhostKeyRequest
import one.mixin.bot.vo.MultisigsRequest
import one.mixin.bot.vo.MultisigsResponse
import one.mixin.bot.vo.OutputResponse
import one.mixin.bot.vo.PinRequest
import one.mixin.bot.vo.Quota
import one.mixin.bot.vo.RpcRequest
import one.mixin.bot.vo.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface UserCallService {
    @POST("users")
    fun createUsersCall(
        @Body request: AccountRequest,
    ): Call<MixinResponse<User>>

    @POST("pin/update")
    fun createPinCall(
        @Body request: PinRequest,
    ): Call<MixinResponse<User>>

    @POST("pin/verify")
    fun pinVerifyCall(
        @Body request: PinRequest,
    ): Call<MixinResponse<User>>

    @POST("pin/update")
    fun updatePinCall(
        @Body request: PinRequest,
    ): Call<MixinResponse<Account>>

    @GET("safe/me")
    fun getMeCall(): Call<MixinResponse<Account>>

    @POST("multisigs/requests")
    fun requestsMultisigsCall(
        @Body request: MultisigsRequest,
    ): Call<MixinResponse<MultisigsResponse>>

    @GET("multisigs/outputs")
    fun multisigsOutputsCall(
        @Query("members") members: List<String>? = null,
        @Query("threshold") threshold: String? = null,
        @Query("state") state: String? = null,
        @Query("offset") offset: String? = null,
        @Query("limit") limit: String? = null,
        @Query("order") order: String? = null,
    ): Call<MixinResponse<List<OutputResponse>>>

    @POST("multisigs/{id}/cancel")
    fun cancelMultisigsCall(
        @Path("id") id: String,
    ): Call<MixinResponse<Void>>

    @POST("multisigs/{id}/sign")
    fun signMultisigsCall(
        @Path("id") id: String,
        @Body pinRequest: PinRequest,
    ): Call<MixinResponse<Void>>

    @POST("multisigs/{id}/unlock")
    fun unlockMultisigsCall(
        @Path("id") id: String,
        @Body pinRequest: PinRequest,
    ): Call<MixinResponse<Void>>

    @POST("outputs")
    fun readGhostKeysCall(
        @Body ghostKeyRequest: GhostKeyRequest,
    ): Call<MixinResponse<GhostKey>>

    @POST("external/proxy")
    fun mixinMainnetRPCCall(
        @Body rpcRequest: RpcRequest,
    ): Call<JsonObject>

    @GET("search/{query}")
    fun searchCall(
        @Path("query") query: String,
    ): Call<MixinResponse<User>>

    @GET("apps/{id}/quotas")
    fun quotasCall(
        @Path("id") id: String,
    ): Call<MixinResponse<List<Quota>>>
}
