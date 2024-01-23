package com.example.apicallstoredataindatabase.database

import com.example.apicallstoredataindatabase.MyProduct
import retrofit2.http.GET

interface DataApi {
    @GET("products")
    fun getProductData(): retrofit2.Call<MyProduct>
}