package com.techspark.mynotes

import android.graphics.Paint
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DividerItemDecoration
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

    //LiveData
    lateinit var noteLiveData: LiveData<MutableList<Note>>
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


    var counter = 0
    lateinit var manager: PreferenceManager
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        view.findViewById<Button>(R.id.btn_save).setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }

        noteLiveData = NoteDb.getInstance(requireContext()).noteDao.get()
        manager = PreferenceManager(requireActivity())

        val adapter = NoteAdapter(
            OnclickListener { item ->
                Thread {
//                    val dao = NoteDb.getInstance(requireContext()).noteDao
//                    dao.delete(item)
                    val dao = NoteDb.getInstance(requireContext()).noteDao
                    item.time = System.currentTimeMillis()
                    dao.edit(item)
                }.start()

            }
        )
        list_note.adapter = adapter


        list_note.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            ).also {
                with(ShapeDrawable(RectShape())) {
                    intrinsicHeight = 10
                    alpha = 0
                    it.setDrawable(this)
                }
            }
        )
        /**
         * ta vaghti ke counter kuchiktar az list.size bud meghdare list[counter] ro beriz tuye textviewList[counter]
         *
         *ozv akhar list ro beriz tuye ozve 0 textviewlist
         * ozv yeki munder be akhare  list ro beriz tuye ozve 1 textviewList
         * ozv 2ta munder be akhare list ro beriz tuye ozve  2 textviewList
         *
         * noteList = {a,b,c,d,e,f,g,h,i,j,k,;,fjm,fs,df,a,df,df,a,df,af,asd,f,}
         * TextViewList = {tv1,tv2,tv3,tv4,tv5}
         *
         * tv1 = e
         * tv2 = d
         * tv3 = c
         * tv4 = b
         * tv5 = a
         *
         * 0 = 4
         * 1 = 3
         * 2 = 2
         * 3 = 1
         * 4 = 0
         *
         */
        noteLiveData.observe(viewLifecycleOwner,{

            (list_note.adapter as NoteAdapter).submitList(it)

        })

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
        val noteText = text_note.text.toString()

        /**
         * Edittext ra khali konad
         */
        text_note.setText("")


        /**
         * Thread a => dao besaz
         * note ro zakhire kon
         * khodafez
         */
        Thread(
            Runnable {
            val dao = NoteDb.getInstance(requireContext()).noteDao
            val note = Note(0,noteText,time,time,1)
            dao.add(note)
        }).start()

        /**
         * Coroutine
         */


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