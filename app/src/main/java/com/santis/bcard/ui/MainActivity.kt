package com.santis.bcard.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.santis.bcard.App
import com.santis.bcard.databinding.ActivityMainBinding
import com.santis.bcard.util.Image

class MainActivity : AppCompatActivity() {
    // implantar o binding
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    // implantar o ViewModel na View
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    private val adapter by lazy { BusinessCardAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.rvItems.adapter = adapter
        getAllBusinessCard()
        insertListeners()
    }

    private fun insertListeners() {
        binding.btnAdd.setOnClickListener {
            val intent = Intent(this@MainActivity, AddBusinessCardActivity::class.java)
            startActivity(intent)
        }

        adapter.listenerShare = { card ->
            Image.share(this@MainActivity, card)
        }
    }

    // Verifica e pega os Cartões
    private fun getAllBusinessCard() {
        mainViewModel.getAll().observe(
            this,
            { businessCards ->
                adapter.submitList(businessCards)
            }
        )
    }
}
