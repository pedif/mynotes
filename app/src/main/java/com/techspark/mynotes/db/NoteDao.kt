package com.techspark.mynotes.db

import androidx.room.*
import com.techspark.mynotes.model.Note

@Dao
interface NoteDao {

    @Insert
    fun add(note: Note)

    @Query("Select * from note")
    fun get(): ArrayList<Note>

    @Update
    fun edit(note: Note)

    @Delete
    fun delete(note: Note)
}