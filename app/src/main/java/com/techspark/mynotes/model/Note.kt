package com.techspark.mynotes.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * val example = Note(1,"armin", 9.50, 10.50, 1)
 * NoteDao.add(example)
 * NoteDao.get()
 *  id       text      time      alarmTime     type
 *  1       "Armin"     9.50        10.50       1
 *
 */
@Entity(tableName = "note")
data class Note(

    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var text:String,
    var time:Long,
    var alarmTime: Long,
    var type: Int

){

}

/**
 * Saving to file  =>
 * edit.putNote //not found
 * edit.putLong("id",)
 * edit.putString("text")
 * edit.putLong("toime
 * edit.putlong("alarm)
 * edit.putint("type:)
 *
 *
 * //Load from file =>
 * edit.getlong
 *
 *
 *
 * Saving to Database =>
 * Dao.Save(Note)
 *
 * Load from Database =>
 * Dao.get()
        **/