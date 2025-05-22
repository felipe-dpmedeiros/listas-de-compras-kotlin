package com.example.trabalhoandroidfelipe.data


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.trabalhoandroidfelipe.model.ItemModel

@Database(entities = [ItemModel::class], version = 1)
abstract class ItemDatabase : RoomDatabase() {


    abstract fun itemDao(): ItemDao
}