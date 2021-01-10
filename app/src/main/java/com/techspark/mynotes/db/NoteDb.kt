package com.techspark.mynotes.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.techspark.mynotes.model.Note

/**
 *
 * Database  is a table
 *
 *
 *
 */
@Database(entities = [Note::class],version = 1)
abstract class NoteDb: RoomDatabase() {

    abstract  val noteDao: NoteDao


    companion object {
        @Volatile
        private var INSTANCE: NoteDb? = null
        private val LOCK = Any()
        private const val dbName = "note_database"
        fun getInstance(context: Context): NoteDb {

            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NoteDb::class.java,
                        dbName
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}