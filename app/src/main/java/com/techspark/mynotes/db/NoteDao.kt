package com.techspark.mynotes.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.techspark.mynotes.model.Note

/***
 * NoteDao.add(note)
 *
 */
@Dao
interface NoteDao {

    /**
     * Java = List, ArrayList
     * Kotlin = List , MutableList
     * List = val
     * MutableList = var
     */
    @Insert
    fun add(note: Note)

    @Query("Select * from note")
    fun get(): LiveData<MutableList<Note>>

    @Update
    fun edit(note: Note)

    @Delete
    fun delete(note: Note)
}