package com.udacity.asteroidradar.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.database.DatabaseImage
import com.udacity.asteroidradar.database.convertDbImgToPicOfDay
import com.udacity.asteroidradar.database.getImgDatabase
import com.udacity.asteroidradar.network.AsteroidApi
import com.udacity.asteroidradar.repository.Repository
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val imgDatabase = getImgDatabase(application)
    val repository = Repository(imgDatabase)

    private val _image = MutableLiveData<List<PictureOfDay>>()
    val image : LiveData<List<PictureOfDay>>
        get() = _image

    init {
        viewModelScope.launch {
            repository.refreshImg()
      //      val img = repository.image.value
            _image.postValue(repository.image.value)
//            Log.e("ViewModel", "${img}")
        }

    }



//    private val _pod = MutableLiveData<PictureOfDay>()
//    val pod: LiveData<PictureOfDay>
//        get() = _pod
//
//
//    init {
//        viewModelScope.launch {
//            accessNASA()
//        }
//
//    }
//
//
//
//    fun accessNASA(){
//        AsteroidApi.retrofitService.getImgOfTheDay().enqueue(object : Callback<String> {
//            override fun onResponse(call: Call<String>, response: Response<String>) {
//                val str = response.body()
//                val jsonObject = JSONObject(str)
//                val mediaType = jsonObject.getString("media_type")
//                val url = jsonObject.getString("url")
//                val title = jsonObject.getString("title")
//                val pic = PictureOfDay(mediaType,title,url)
//                Log.i("PicDay", pod.toString())
//                _pod.value = pic
//
//            }
//
//            override fun onFailure(call: Call<String>, t: Throwable) {
//                Log.i("Failure", t.message!!)
//            }
//
//        })
//    }
}