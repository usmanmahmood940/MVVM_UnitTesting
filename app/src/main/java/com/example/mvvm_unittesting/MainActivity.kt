package com.example.mvvm_unittesting

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mvvm_unittesting.adapter.ProductAdapter
import com.example.mvvm_unittesting.databinding.ActivityMainBinding
import com.example.mvvm_unittesting.util.NetworkResult
import com.example.mvvm_unittesting.viewmodel.MainViewModel
import com.example.mvvm_unittesting.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {


    lateinit var mainViewModel: MainViewModel
    lateinit var adapter : ProductAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.productList.layoutManager = GridLayoutManager(this, 2)

        val repository = (application as StoreApplication).productRepository
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository))
            .get(MainViewModel::class.java)

        mainViewModel.getProducts()

        mainViewModel.products.observe(this) {
            when (it) {
                is NetworkResult.Success -> {
                    Log.d("CHEEZ", it.data.toString())
                    adapter = ProductAdapter(it.data!!)
                    binding.productList.adapter = adapter

                }

                is NetworkResult.Error -> {}
                is NetworkResult.Loading -> {}
                }
            }
        }

}