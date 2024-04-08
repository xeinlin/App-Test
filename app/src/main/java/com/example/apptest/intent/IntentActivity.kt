package com.example.apptest.intent

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.example.apptest.BaseActivity
import com.example.apptest.databinding.ActivityIntentBinding

class IntentActivity : BaseActivity<ActivityIntentBinding>() {

    companion object {
        private const val MESSAGE = "message From main"
        fun getInstance(context: Context, msg: String): Intent {
            return Intent(context, IntentActivity::class.java).also {
                it.putExtra(MESSAGE, msg)
            }
        }
    }

    override val pageTitle: String get() = "Intent"
    override fun setUpViewBinding(layoutInflater: LayoutInflater): ActivityIntentBinding {
        return ActivityIntentBinding.inflate(layoutInflater)
    }

    private var imageUri: Uri? = null

    private val fileChooserConontract =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                imageUri = it
                binding.btnShareContent.visibility = View.VISIBLE
                binding.imageView.setImageURI(it)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val meassage = intent.getStringExtra(MESSAGE)
        binding.tvMessage.text = meassage
        binding.btnShareContent.visibility = View.GONE

        binding.btnOpenLink.setOnClickListener { this.openLink() }
        binding.btnPhoneCall.setOnClickListener { this.callPhoneNumber() }
        binding.btnShareContent.setOnClickListener { this.shareContent() }
        binding.btnOpenMap.setOnClickListener { this.openMap() }
        binding.btnTakePicture.setOnClickListener { this.takePicture() }
    }

    private fun openLink() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"))
        startActivity(intent)
    }

    private fun callPhoneNumber() {}

    private fun shareContent() {
        this.imageUri?.let {
            val intent = Intent(Intent.ACTION_SEND)
            intent.setType("image/*")
            intent.putExtra(Intent.EXTRA_STREAM, it)
            startActivity(intent)
        }
    }

    private fun openMap() {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://www.google.com/maps/@12.9594868,100.8906177,16z?entry=ttu")
        )
        startActivity(intent)
    }

    private fun takePicture() {
        fileChooserConontract.launch("image/*")
    }
}