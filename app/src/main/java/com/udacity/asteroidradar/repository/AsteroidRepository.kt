package com.udacity.asteroidradar.repository

import android.util.Log
import com.udacity.asteroidradar.database.DatabaseImage
import com.udacity.asteroidradar.database.ImageDatabase
import com.udacity.asteroidradar.database.convertDbImgToPicOfDay
import com.udacity.asteroidradar.network.AsteroidApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.await

class Repository(val imgDatabase: ImageDatabase){

    suspend fun refreshImg(){
          withContext(Dispatchers.IO){
              val imgUrl = AsteroidApi.retrofitService.getImgOfTheDay().await()
              val jsonObject = JSONObject(imgUrl)
              val mediaType = jsonObject.getString("media_type")
              val url = jsonObject.getString("url")
              val title = jsonObject.getString("title")
              val img = DatabaseImage(mediaType,title,url)
              imgDatabase.imgDao.insert(img)
              val sora = DatabaseImage("image","good","https://apod.nasa.gov/apod/image/2109/DancingGhosts_EnglishNorris_960.jpg")
              imgDatabase.imgDao.insert(sora)

          }
    }

    val image = convertDbImgToPicOfDay(imgDatabase.imgDao.getImage())

//    suspend fun refreshImg(){
//        withContext(Dispatchers.IO){
//            val imgUrl = AsteroidApi.retrofitService.getImgOfTheDay().await()
//            Log.i("ImgUrl",imgUrl)
//            val jsonObject = JSONObject(imgUrl)
//            val mediaType = jsonObject.getString("media_type")
//            val url = jsonObject.getString("url")
//            val title = jsonObject.getString("title")
//            val img = Image(mediaType,url,title)
//            imgDatabase.imgDao.insert(img)
//        }
//    }

}