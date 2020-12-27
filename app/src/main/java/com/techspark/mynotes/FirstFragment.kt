package com.techspark.mynotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.techspark.mynotes.db.NoteDb
import com.techspark.mynotes.db.PreferenceManager
import com.techspark.mynotes.model.Note
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
    lateinit var manager: PreferenceManager
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        view.findViewById<Button>(R.id.btn_save).setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }

        manager = PreferenceManager(requireActivity())
        textViewList = ArrayList<TextView>()
        textViewList.add(text_0)
        textViewList.add(text_1)
        textViewList.add(text_2)
        textViewList.add(text_3)
        textViewList.add(text_4)

        var abc = 0
        while(abc<textViewList.size){
            textViewList[abc].text = manager.getNote(abc)
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
        val time:Long = calendar.timeInMillis

        var currentDateTimeString: String =java.text.DateFormat.getDateTimeInstance().format(Date()) // 12/13/2020, 11:11 AM
        var a = SimpleDateFormat("EEE, MMM d, ''yy hh:mm:ss")
        var timeLabel = a.format(Date())

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
        textViewList[counter].text = timeLabel

        /**
         * timeview[counter].text = time
         */

        /**
         * Save kone note ro
         */
//        manager.setNote(note,counter)
        manager.save(note,counter,PreferenceManager.TYPE_NOTE)
        manager.save(timeLabel,counter,PreferenceManager.TYPE_TIME)
        manager.save("asdf", counter, "aape")
        manager.save("asdfasdfsad",counter,PreferenceManager.TYPE_CAR)



        manager.setTime(time, counter)
        manager.setNote(note, counter)
        /**
         * manager.setAape("adfc",counter)
         * managre.setCar("akdjshfjk", counter)
         */

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



    var a = 30

    fun  number(){
         a = 40
        Toast.makeText(requireContext(),a.toString(),Toast.LENGTH_SHORT).show()
    }

    fun number2(){
        Toast.makeText(requireContext(),a.toString(),Toast.LENGTH_SHORT).show()
    }

}