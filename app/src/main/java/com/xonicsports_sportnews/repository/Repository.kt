package com.xonicsports_sportnews.repository
import com.xonicsports_sportnews.network.Api
import  com.xonicsports_sportnews.network.SafeAPIRequest
class Repository(private val api: Api) : SafeAPIRequest() {
    suspend fun getData() = apiRequest {
        api.getData()
    }
}