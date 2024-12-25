package com.example.users

import com.example.user_db.myData
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("users")
    fun getUserData(): Call<myData>
}