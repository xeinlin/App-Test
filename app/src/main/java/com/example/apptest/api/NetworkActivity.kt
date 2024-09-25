@file:Suppress("DEPRECATION")

package com.example.apptest.api

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.example.apptest.BaseActivity
import com.example.apptest.api.model.Movie
import com.example.apptest.database.AddProductFragment
import com.example.apptest.databinding.ActivityNetworkBinding
import com.example.apptest.helper.ScreenAnimation
import com.example.apptest.helper.attachProgress
import com.example.apptest.helper.showDialogFragment
import com.example.apptest.helper.showToast
import com.example.apptest.viewmodel.MyVM
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.androidx.viewmodel.ext.android.viewModel

@Suppress("DEPRECATION")
class NetworkActivity : BaseActivity<ActivityNetworkBinding>() {

    companion object {

        fun getInstance(context: Context): Intent {
            return Intent(context, NetworkActivity::class.java)
        }

    }

    override val pageTitle: String = "Networking"

    override fun setUpViewBinding(layoutInflater: LayoutInflater): ActivityNetworkBinding {
        return ActivityNetworkBinding.inflate(layoutInflater)
    }

    private val myVM: MyVM by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.fetchMovies()

        binding.swipeRefreshLayout.setOnRefreshListener {
            this.fetchMovies()
        }
    }

    override fun onStart() {
        super.onStart()

        myVM.movieSubject
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { showToast("Movie Count = ${it.results.count()}") },
                Throwable::printStackTrace
            )
            .addTo(disposable)
    }

    private fun fetchMovies() {
        this.myVM.fetchMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .attachProgress(binding.swipeRefreshLayout)
            .subscribe()
            .addTo(disposable)
    }

    /*private fun fetchMovies() {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://api.themoviedb.org/3/discover/movie?language=en-US&page=1")
            .get()
            .addHeader("accept", "application/json")
            .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2MGIzMjBhMjk2MmEyODEyYzM2ZDFmNGJmZWU2Y2IyNSIsIm5iZiI6MTcyNDE1Mzg3Ny42MTcwODgsInN1YiI6IjYxODVmYWZiZDQ4Y2VlMDA2MmQyZTQ5OSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.1OF4x9sf6p6gEzChm2xvSLd2_yi-osVt6dVqMz0PQYU")
            .build()

        AsyncTask.execute {
            val response = client.newCall(request).execute()
            val body = response.body?.string()

            Log.d("TEST_LOG", "Status Code: ${response.code}")
            Log.d("TEST_LOG", "Success: ${response.isSuccessful}")
            Log.d("TEST_LOG", "Body: $body")

            var moviePage: MoviePage? = null

            body?.let {
                try {
                    val gson = Gson()
                    moviePage = gson.fromJson(it, MoviePage::class.java)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            runOnUiThread {
                binding.swipeRefreshLayout.isRefreshing = false
                moviePage?.let {
                    showToast("Movie Count = ${it.results.count()}")
                }
            }
        }
    }*/

    private fun testAuthentication() {
        val client = OkHttpClient()

        val request = Request.Builder().url("https://api.themoviedb.org/3/authentication").get()
            .addHeader("accept", "application/json").addHeader(
                "Authorization",
                "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2MGIzMjBhMjk2MmEyODEyYzM2ZDFmNGJmZWU2Y2IyNSIsIm5iZiI6MTcyNDE1Mzg3Ny42MTcwODgsInN1YiI6IjYxODVmYWZiZDQ4Y2VlMDA2MmQyZTQ5OSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.1OF4x9sf6p6gEzChm2xvSLd2_yi-osVt6dVqMz0PQYU"
            ).build()

        AsyncTask.execute {
            val response = client.newCall(request).execute()

            Log.d("TEST_LOG", "Status Code: ${response.code}")
            Log.d("TEST_LOG", "Success: ${response.isSuccessful}")
            Log.d("TEST_LOG", "Body: ${response.body?.string()}")
        }
    }

    private fun setupCustomAdapter(movies: List<Movie>) {
        val adapter = GridAdapter(movies)

        binding.rvMovie.setOnItemClickListener { _, _, position, _ ->
            val     item = adapter.getItem(position) as? Movie
            val name = item?.title ?: "Unknown"
            this@NetworkActivity.showToast(name)
            showDialogFragment(
                AddProductFragment.getInstance(),
                animation = ScreenAnimation.ENTER_UP_EXIT_STAY
            )

        }
        binding.rvMovie.adapter = adapter
    }

}

private class GridAdapter(val items: List<Movie>) : BaseAdapter() {
    override fun getCount(): Int {
        return items.count()
    }

    override fun getItem(p0: Int): Any {
        return items[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    @SuppressLint("ViewHolder")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val binding = GridViewItemBinding.inflate(LayoutInflater.from(p2?.context))
        binding.title.text = items[p0].title
        binding.desc.text = items[p0].release_date
        val posterUrl =
            "https://image.tmdb.org/t/p/w154${items[p0].poster_path}"

        Glide.with(binding.image.context)
            .load(posterUrl)
            .into(binding.image)

        return binding.root
    }

}

