package com.techspark.mynotes

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import kotlinx.android.synthetic.main.fragment_first.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    /**
     * Requirements:
     * Save kardan  =>  File, Database, Cloud
     * Reminder
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }


    lateinit var textViewList:ArrayList<TextView>
    val timeView = ArrayList<TextView>()
    var counter = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        view.findViewById<Button>(R.id.btn_save).setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }

        textViewList = ArrayList<TextView>()
        textViewList.add(text_0)
        textViewList.add(text_1)
        textViewList.add(text_2)
        textViewList.add(text_3)
        textViewList.add(text_4)

        var abc = 0
        while(abc<textViewList.size){
            textViewList[abc].text = getNote(abc)
            abc++
        }

        view.findViewById<Button>(R.id.btn_save).setOnClickListener {

            addNote()
            number()
            number2()
        }

    }

    /**
            harbar ruye btn_Save click shavad man yek text daryaft mikonam
            baraye har bar in text ra zakhire konad
     **/

    fun addNote() {
        val calendar = Calendar.getInstance()

        var currentDateTimeString: String =java.text.DateFormat.getDateTimeInstance().format(Date()) // 12/13/2020, 11:11 AM
        var a = SimpleDateFormat("EEE, MMM d, ''yy hh:mm:ss")
        var ourFormat = a.format(Date())

        /**
         *  12/13/2020, 11:11AM
         *  13/12/2020, 11:11 AM
         *  13/December/2020 11:11 Am
         *  11/Dec/ 2020 11:11 Am
         *  11/Dec/2020 11:11
         */

        /**
         * String currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());
         * var currentDateTimeString: String =
         */

        /**
         * Biad text ro daryaft konad
         */
        val note = text_note.text.toString()

        /**
         * Edittext ra khali konad
         */
        text_note.setText("")


        /**
         * Textview ra be tartib por kon
         */
        textViewList[counter].text = note
        textViewList[counter].text = ourFormat

        /**
         * timeview[counter].text = time
         */

        /**
         * Save kone note ro
         */
        saveNote(note,counter)

        counter++

        if (counter == 5)
            counter = 0

        //objective => savenote betavanad item haye mokhtalef ra zakhire konad
        /**
         * Item 1 = armin
         * item 2= faran
         * item 3 = pedram
         */
    }

    fun saveNote(text: String, id: Int){
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

    var a = 30

    fun  number(){
         a = 40
        Toast.makeText(requireContext(),a.toString(),Toast.LENGTH_SHORT).show()
    }

    fun number2(){
        Toast.makeText(requireContext(),a.toString(),Toast.LENGTH_SHORT).show()
    }

    fun gitTest():String{
        return ""
    }
}