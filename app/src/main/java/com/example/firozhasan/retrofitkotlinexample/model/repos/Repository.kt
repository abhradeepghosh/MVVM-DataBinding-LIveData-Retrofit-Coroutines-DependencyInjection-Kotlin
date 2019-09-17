package com.example.firozhasan.retrofitkotlinexample.model.repos

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.firozhasan.retrofitkotlinexample.model.api.JobAPI
import com.example.firozhasan.retrofitkotlinexample.model.api.JobServices
import com.example.firozhasan.retrofitkotlinexample.model.modelClass.Country
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

object Repository {

    private var apiclient: JobServices? = null

    /*this variable are for getting country name by giving capital name*/
    private val _currentCountryName = MutableLiveData<String>()
    val currentCountryName: LiveData<String>
        get() = _currentCountryName

    /*this variable provides list of countries*/
    val _countires: MutableLiveData<List<Country>> = MutableLiveData()
    val getAllCountires: LiveData<List<Country>>
        get() = _countires


    init {
        _currentCountryName.value = "N/A"
        apiclient = JobAPI.client.create(JobServices::class.java)
    }

    fun getCountyNameByCapital(capital: String
    ) {
        val call = apiclient?.getKountry(capital)

        call?.enqueue(object : Callback<List<Country>> {
            override fun onFailure(call: Call<List<Country>>?, t: Throwable?) {
                Log.d("failure", t.toString())
            }

            override fun onResponse(call: Call<List<Country>>?, response: Response<List<Country>>?) {
                if (response?.isSuccessful!!) {
                    var results = response?.body()?.get(0)?.name
                    Log.d("success", results)
                    //    countryTV?.setText(results)
                    _currentCountryName.value = results.toString()


                }


            }


        })
    }


    fun getALLCounty() {
        val call = apiclient?.getAllCountries()

        call?.enqueue(object : Callback<List<Country>> {
            override fun onFailure(call: Call<List<Country>>?, t: Throwable?) {
                Log.d("failure", t.toString())
            }

            override fun onResponse(call: Call<List<Country>>?, response: Response<List<Country>>?) {
                if (response?.isSuccessful!!) {
                    var results = response?.body()?.size
                    Log.d("success", results.toString())
                    _countires.value = response.body()

                }


            }


        })
    }
}