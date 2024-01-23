package com.example.apicallstoredataindatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.apicallstoredataindatabase.database.ApiDataDao
import com.example.apicallstoredataindatabase.database.AppDatabase
import com.example.apicallstoredataindatabase.database.DataApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private lateinit var myadapter: Myadapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var apiDataDao: ApiDataDao
    lateinit var model: ApiData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)

        val retrofit = Retrofit.Builder().baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create()).build().create(DataApi::class.java)

        val database = Room.databaseBuilder(
            applicationContext, AppDatabase::class.java, "my_database"
        ).build()

        apiDataDao = database.apiDataDao()

        val retrofitData = retrofit.getProductData()

        retrofitData.enqueue(object : Callback<MyProduct?> {
            override fun onResponse(call: Call<MyProduct?>, response: Response<MyProduct?>) {
                val responseBody = response.body()
                val productList = responseBody?.products


                Executors.newSingleThreadExecutor().execute {
                    for (product in productList!!) {
                        apiDataDao.insertApiDataIntoDatabase(
                            ApiData(0, product.thumbnail, product.title, product.brand)
                        )
                    }
                }

//                myadapter = Myadapter(this@MainActivity, productList as ArrayList)
//                recyclerView.adapter = myadapter
//                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            }

            override fun onFailure(call: Call<MyProduct?>, t: Throwable) {
            }
        })

        database.apiDataDao().getAllApiData().observe(this, Observer { list ->
            myadapter = Myadapter(this@MainActivity, list as ArrayList)
            recyclerView.adapter = myadapter
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        })
    }
}