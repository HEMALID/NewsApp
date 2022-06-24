package com.example.newsapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.recycle1.Recycler1Adapter
import com.example.newsapp.recycler2.ApiInterface
import com.example.newsapp.recycler2.Recycler2Adapter
import com.example.newsapp.recycler2.model.Data
import com.example.newsapp.recycler2.model.DataX
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL="https://inshorts.deta.dev/"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var userList1: ArrayList<DataX>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Recycler 1

        var linearLayoutManager=LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recycle1.layoutManager=linearLayoutManager
        var adapter=Recycler1Adapter(this,object : Recycler1Adapter.newsAction{
            override fun onClick(n: String) {
                userList1.clear()
                getRecycler2Data(n)
            }
        })
        binding.recycle1.adapter=adapter

        //Recycler 2

        userList1=arrayListOf()
        var llm=LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recycler2.layoutManager=llm

        getRecycler2Data(" ")

    }
    private fun getRecycler2Data(category: String) {
        val retrofitBuilder=Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        var retrofitData1=retrofitBuilder.getData2(category)
        retrofitData1.enqueue(object : Callback<Data> {
            override fun onResponse(
                call: Call<Data>,
                response: Response<Data>,
            ) {
                binding.shimmerViewContainer.stopShimmer()
                binding.recycle1.visibility = View.VISIBLE
                binding.shimmerViewContainer.visibility = View.GONE

                binding.shimmerViewContainer1.stopShimmer()
                binding.recycler2.visibility = View.VISIBLE
                binding.shimmerViewContainer1.visibility = View.GONE


                val responseBody=response.body()
                userList1.addAll(responseBody?.data!!)
                var adapter1=Recycler2Adapter(baseContext, userList1,this@MainActivity)
                binding.recycler2.adapter=adapter1
                Log.e("Success", responseBody.toString())
            }

            override fun onFailure(call: Call<Data>, t: Throwable) {
                Log.e("MainActivity", "onFail" + t.message)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        binding.shimmerViewContainer.startShimmer()
        binding.shimmerViewContainer1.startShimmer()
    }

    override fun onPause() {
        super.onPause()
        binding.shimmerViewContainer.stopShimmer()
        binding.shimmerViewContainer1.stopShimmer()
    }

}