package com.samarth.musicapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.google.android.material.snackbar.Snackbar
import com.samarth.musicapp.databinding.ActivityMainBinding
import com.samarth.musicapp.utils.ConnectionLiveData
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var connectionHelper: ConnectionLiveData

    var snackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        connectionLiveData.value = connectionHelper.isNetworkConnected()

        connectionHelper.observe(
            this
        ) {
            connectionLiveData.value = it
        }

        connectionLiveData.observe(
            this
        ) {
            if (it == false) {
                checkConnection()
            }
            else{
                snackbar?.dismiss()
            }
        }
    }

    private fun showSnackbar() {
        if (snackbar == null) {
            //init snackbar
            snackbar = Snackbar.make(
                binding.root,
                R.string.snackbar_no_network,
                Snackbar.LENGTH_INDEFINITE
            )
                .setAction(R.string.snackbar_no_network_action) {
                    checkConnection()
                }
        }
        //show
        snackbar!!.show()
    }

    private val handler = Handler(Looper.getMainLooper())
    private fun checkConnection() {
        handler.postDelayed(checkConnectionRunnable, 500)
    }

    private val checkConnectionRunnable = Runnable {
        if (!connectionHelper.isNetworkConnected()) {
            showSnackbar()
        }
    }

    companion object {
        var connectionLiveData = MutableLiveData(false)
    }
}