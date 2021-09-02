package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.map
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.udacity.asteroidradar.PictureOfDay

@Entity
data class DatabaseImage(@PrimaryKey val mediaType: String, val title: String, val url: String)


fun convertDbImgToPicOfDay(dbImg: LiveData<List<DatabaseImage>>): LiveData<List<PictureOfDay>>{
    return dbImg.map{
        it.map{ PictureOfDay(
            mediaType = it.mediaType,
            title = it.title,
            url = it.url
        )}
    }
}

