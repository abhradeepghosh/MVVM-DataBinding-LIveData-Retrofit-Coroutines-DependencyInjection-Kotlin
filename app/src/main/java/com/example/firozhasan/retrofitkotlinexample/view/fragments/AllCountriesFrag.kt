package com.example.firozhasan.retrofitkotlinexample.view.fragments


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.firozhasan.retrofitkotlinexample.databinding.AllCountriesFragBinding
import com.example.firozhasan.retrofitkotlinexample.view.activities.CountryDetails
import com.example.firozhasan.retrofitkotlinexample.view.adapters.CountriesAdapter
import com.example.firozhasan.retrofitkotlinexample.view.adapters.CountryListener
import com.example.firozhasan.retrofitkotlinexample.viewModel.AllCountriesViewModel
import com.example.firozhasan.retrofitkotlinexample.viewModel.AllCountriesViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


/**
 * A simple [Fragment] subclass.
 */
class AllCountriesFrag : Fragment(), KodeinAware {
    override val kodein by kodein()
    private val factory: AllCountriesViewModelFactory by instance()
    private lateinit var viewModel: AllCountriesViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = AllCountriesFragBinding.inflate(inflater)
        binding.lifecycleOwner = this
        viewModel = ViewModelProviders.of(this, factory).get(AllCountriesViewModel::class.java)
        binding.allCountiresViewmodel = viewModel
        binding.countriesRV.adapter = CountriesAdapter(CountryListener {
           // viewModel.displayCountryDetails(it)
            val intent = Intent(activity, CountryDetails::class.java)
            intent.putExtra("alpha", it.alpha2Code)
            startActivity(intent)
        })
        return binding.root
    }
}
