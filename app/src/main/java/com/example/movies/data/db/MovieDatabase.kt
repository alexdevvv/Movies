package com.example.movies.data.db

import android.content.Context
import com.example.movies.domain.models.Movie
import androidx.room.*


const val DB_NAME = "movie_db"

const val DB_VERSION = 1

@Database(entities = arrayOf(Movie::class), version = DB_VERSION, exportSchema = false)

abstract class MovieDatabase: RoomDatabase() {

    companion object{

        private var movieDatabase: MovieDatabase? = null

        fun getDbInstance(mContext: Context): MovieDatabase{
            if (movieDatabase == null){
                movieDatabase = Room.databaseBuilder(mContext, MovieDatabase::class.java, DB_NAME)
                    .build()
            }
            return movieDatabase as MovieDatabase
        }
    }

    abstract fun getMoviedao(): MovieDao
}
