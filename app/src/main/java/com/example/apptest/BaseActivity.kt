package com.example.apptest

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.apptest.helper.AirplaneModeChange
import com.example.apptest.helper.ScreenChange
import com.example.apptest.helper.showToast
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: VB
    abstract val pageTitle: String

    abstract fun setUpViewBinding(layoutInflater: LayoutInflater): VB

    val disposable by lazy { CompositeDisposable() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = setUpViewBinding(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = pageTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(this is MainActivity)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Toast.makeText(this, "onNewIntent", Toast.LENGTH_LONG).show()
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
        disposable.clear()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: AirplaneModeChange) {
        showToast("Airplane Mode Changed")
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: ScreenChange) {
        showToast("Screen Changed")
    }


}