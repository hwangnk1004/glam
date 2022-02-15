package com.example.cupist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.cupist.R
import com.example.cupist.databinding.ActivityMainBinding
import com.example.cupist.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var carModelViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
    }

    private fun initViewModel() {
        carModelViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.lifecycleOwner = this
        binding.mainViewModel = carModelViewModel
    }

    override fun onClick(v: View?) {

        when(v) {

        }
    }
}