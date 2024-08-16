package com.lihan.jiburi.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lihan.jiburi.data.local.dao.FilmDao
import com.lihan.jiburi.data.local.entity.FilmEntity


@Database(
    entities = [ FilmEntity::class],
    version = 1
)
abstract class JiburiRoomDatabase : RoomDatabase(){
    abstract val filmDao: FilmDao
}