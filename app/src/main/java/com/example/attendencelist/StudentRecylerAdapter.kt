package com.example.attendencelist

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//en context för att kunna skapa en inflater -> så vi kan skapa en view ut ifrån Xml filer.
//ta in lista som man ska använda.
//det här klassen Arv av RecyclerView.Adapter()

class StudentRecylerAdapter (val context: Context, val students: List<Student>)
      : RecyclerView.Adapter<StudentRecylerAdapter.ViewHolder>() {

    val layoutInflater = LayoutInflater.from(context)


    //det kommer köras när vi skapa en ViewHolder.
    //med hjälp av Inflater skapa en ny view.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.list_item, parent, false)

        return ViewHolder(itemView)
    }


    //lägga in info i det View som finns innuti ViewHolder. Rätt info i rätt ruta
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student = students[position]

        holder.nameTextView.text = student.name
        holder.classTextView.text = student.className
        holder.presentButton.isChecked = student.present
        holder.studentPosition = position
    }


    //det ska det vara så många som det finns studenter
    // det ska return student.size
    override fun getItemCount() = students.size

    fun removeStudent(position: Int){
        DataManager.students.removeAt(position)
        notifyDataSetChanged()
    }


//ange en ny class som vi kommer använda oss av här inne. som kapslar in
// en viewHolder -> som håller i view för varje cell/rad i vårt RecyclerView.
//det är klassen ska i sig Arv av RecyclerView.ViewHolder.
    inner  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val nameTextView = itemView.findViewById<TextView>(R.id.nameTextView)
    val classTextView = itemView.findViewById<TextView>(R.id.classTextView)
    val presentButton = itemView.findViewById<CheckBox>(R.id.checkBox)
    val deleteButton =  itemView.findViewById<ImageButton>(R.id.deleteButton)
    var studentPosition = 0

    init {
        itemView.setOnClickListener{
            val intent = Intent(context,CreateAndEditStudentActivity::class.java)
            intent.putExtra(STUDENT_POSITION_KEY, studentPosition)
            context.startActivity(intent)
        }

        presentButton.setOnClickListener{
            DataManager.students[studentPosition].present = presentButton.isChecked
        }

        deleteButton.setOnClickListener{
            removeStudent(studentPosition)
        }
    }

    }
}