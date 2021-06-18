package com.iswan.main.maps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.dicoding.tourismapp.di.MapsDependencies
import com.iswan.main.core.data.Resource
import com.iswan.main.maps.databinding.ActivityMapsBinding
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class MapsActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: MapsViewModelFactory

    private val mapsViewModel: MapsViewModel by viewModels { factory }

    private fun initInjection() {
        DaggerMapsComponent.builder()
            .context(this)
            .mapsDependencies(EntryPointAccessors.fromApplication(
                applicationContext, MapsDependencies::class.java
            ))
            .build()
            .inject(this)
    }

    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        initInjection()

        binding = ActivityMapsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.title = "Tourism Maps"

        getTourismData()
    }

    private fun getTourismData() {
        mapsViewModel.tourism.observe(this, { tourism ->
            if (tourism != null) {
                when(tourism) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.tvMaps.text = "This is map of ${tourism.data?.get(0)?.name}"
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.tvError.visibility = View.VISIBLE
                        binding.tvError.text = tourism.message
                    }
                }
            }
        })
    }
}