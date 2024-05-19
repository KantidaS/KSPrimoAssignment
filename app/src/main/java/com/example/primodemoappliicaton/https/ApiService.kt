package com.example.primodemoappliicaton.https

import com.example.primodemoappliicaton.model.Feed
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    interface FeedApi {
        @GET("feed/@ascend-agency")
        fun getFeed(): Call<Feed>
    }
}