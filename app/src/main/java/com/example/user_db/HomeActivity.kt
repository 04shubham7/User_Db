package com.example.user_db

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.user_db.databinding.ActivityHomeBinding
import com.example.user_db.databinding.ActivityMainBinding
import com.example.users.ApiInterface
import com.example.users.MyAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var myAdapter: MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getUserData()

        retrofitData.enqueue(object : Callback<myData?> {
            override fun onResponse(p0: Call<myData?>, p1: Response<myData?>) {
//if api call is success
                val responseBody = p1.body()
                val myStringBuilder = StringBuilder()
                val productList = responseBody?.users!!
//                 val collectDatainStringB=StringBuilder()
//                     for(myData in productList!!){
//                         collectDatainStringB.append(myData.firstName+" " )
//                     }
//                     val tv=findViewById<TextView>(R.id.tv1)
//                    tv.text=collectDatainStringB
                myAdapter = MyAdapter(this@HomeActivity, productList)
                binding.rv2.adapter = myAdapter
                binding.rv2.layoutManager = LinearLayoutManager(this@HomeActivity)

            }


            override fun onFailure(p0: Call<myData?>, p1: Throwable) {
//               if api call fails
                Log.d("MainActivity", "onFailure:" + p1.message)
            }
        })
    }
}