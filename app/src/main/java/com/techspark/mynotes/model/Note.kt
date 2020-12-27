package com.techspark.mynotes.model

import androidx.room.Entity
import androidx.room.PrimaryKey

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