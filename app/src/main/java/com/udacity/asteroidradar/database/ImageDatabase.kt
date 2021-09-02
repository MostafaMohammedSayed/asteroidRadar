package com.udacity.asteroidradar.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.udacity.asteroidradar.PictureOfDay

@Dao
interface ImageDao{
    @Query("Select * From databaseimage")
    fun getImage(): LiveData<List<DatabaseImage>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg img: DatabaseImage)

    @Query("Delete From databaseimage")
    fun delete()
}

@Database(entities = [DatabaseImage::class],version = 1,exportSchema = false)
abstract class ImageDatabase: RoomDatabase(){
    abstract val imgDao: ImageDao
}

lateinit var IMGINSTANCE: ImageDatabase

fun getImgDatabase(context: Context): ImageDatabase{
    synchronized(ImageDatabase::class.java){
        if (!::IMGINSTANCE.isInitialized){
            IMGINSTANCE = Room.databaseBuilder(context.applicationContext,
            ImageDatabase::class.java,
            "images").build()
        }
    }
    return IMGINSTANCE
}

