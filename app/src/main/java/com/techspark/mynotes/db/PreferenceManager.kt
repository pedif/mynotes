package com.techspark.mynotes.db

import android.app.Activity
import android.content.Context

class PreferenceManager(private val activity: Activity) {

    companion object{

        const val TYPE_NOTE = "note"
        const val TYPE_TIME ="time"
        const val TYPE_CAR = "car"
    }


    /**
     * pedram =>pedram1
     * armin =>armin2
     *save(apple, 0, note) => edit.putString(apple0, note) =>edit.putString(note0,apple)
     *                                                       edit.putString(note0,apple)
     *
     *Data Type = String, Boolean ,Int, Time, char, Fragment, Activity,
     * Data type 1= Primitive Datatype => String, Int, Boolean, Long, Float
     * -9999999999 , +99999999999
     * Data = 0,1,
     * Boolean = 1 Bit => 0,1
     * Int = 8 bit =>  00000000, 11111111  =>
     * 8 => 8ta , 18 => 1*10 + 8*1  = 18 , 137 => 1*100(10^2) + 3*(10^1) + 7*(10^0)
     * 1 => 1, 0 => 0, 2//error =>
     * Data type = Complex data Type => Fragment, Activity, Time
     *
     */
    fun save(text:String , id:Int, name: String){
        val pref = activity?.getPreferences(Context.MODE_PRIVATE)
        val edit = pref?.edit()
        if (edit != null) {
            edit.putString("$name$id", text)
            edit.apply()
        }

    }

    /**
     * Edit.putstring() => Key/value pair
     * PreferenceManager =>
     * Key              Value
     * time0            9:38Am
     * time1            10:00AM
     * time1            armin
     * time1            faran
     * setTime(armin, 1) =>
     * setTime(faran, 1) =>
     */
    fun setTime(text: Long, id:Int){
        /** id:Int = 0,1,2,100,1237192873
         * id:String = "a", "ped", "@" , "0"
         * id:Boolean= false , true  =>"false", "true"
         * $id => id.toString()
        */

        val pref = activity?.getPreferences(Context.MODE_PRIVATE)
        val edit = pref?.edit()
        if (edit != null) {
            edit.putLong("time$id", text)
            edit.apply()
        }
    }

    /**
     * setNote(apple, 0) => edit.putString(note0,apple)
     */
    fun setNote(text: String, id: Int){
        // "a"+b
        //"a$text"
        /**
         * var day = 6
         * Java = "Today is" + day +" of this month"   =>  Today is 6 of this month
         * Kotlin = "Today is $day of this month"  => Today is 6 of this month
         * Kotlin = "Today is day of this month" => Today is day of this month
         */
        /**
         * id  = 0  => kelid = note0
         * id = 1 =>kelid = note1
         */
        //
        val pref = activity?.getPreferences(Context.MODE_PRIVATE)
        val edit = pref?.edit()
        if (edit != null) {
            edit.putString("note$id", text)
            edit.apply()
        }

    }

    fun getTime(id: Int):String{
        val pref = activity?.getPreferences(Context.MODE_PRIVATE)

        return pref?.getString("time$id","").toString()
    }

    fun getNote(dini: Int):String {
        /**
         * Armin = 0 =>   note0
         * armin = 1 => note1
         */

        val a = "" // yek string ke mohtavash khalie
        val b = null//hichi
        val pref = activity?.getPreferences(Context.MODE_PRIVATE)

        return pref?.getString("note$dini","").toString()
    }
}