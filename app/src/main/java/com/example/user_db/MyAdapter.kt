package com.example.users

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

//import androidx.compose.ui.semantics.text
import androidx.recyclerview.widget.RecyclerView
import com.example.user_db.R
import com.example.user_db.User
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class MyAdapter(val context: Context,val userList:List<User>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var firname:TextView
        var lasname:TextView
        var age:TextView
        var img: ShapeableImageView
        var email:TextView
        var company:TextView
        init {
            firname=itemView.findViewById(R.id.fname)
            lasname=itemView.findViewById(R.id.lname)
            age=itemView.findViewById(R.id.uage)
            img=itemView.findViewById(R.id.uimg)
            email=itemView.findViewById(R.id.uemail)
            company=itemView.findViewById(R.id.ucompany)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        var itemView= LayoutInflater.from(context).inflate(R.layout.each_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        val currentItem=userList[position]
        holder.firname.text=currentItem.firstName
        holder.lasname.text=currentItem.lastName
        holder.age.text=currentItem.age.toString()
        holder.email.text=currentItem.email
        holder.company.text=currentItem.company.name
//        for image
        Picasso.get().load(currentItem.image).into(holder.img)
        holder.itemView.setOnClickListener{
            Toast.makeText(context,currentItem.firstName,Toast.LENGTH_SHORT).show()
        }
        holder.itemView.setOnClickListener {
            var dialog = Dialog(context)
            dialog.setContentView(R.layout.custom)
            dialog.setCancelable(true)

            dialog.setOnShowListener {
                var textview = dialog.findViewById<TextView>(R.id.tv3) // Assuming tv3 is an existing TextView in your custom layout

                // Concatenate the information into a single string
//                val userInfo = """
//
//            Last Name: ${holder.lasname.text}
//            Age: ${holder.age.text}
//            Email: ${holder.email.text}
//            Company: ${holder.company.text}
//            ID: ${currentItem.id}
//            Password: ${currentItem.password} // Consider security implications
//            Phone: ${currentItem.phone}
//
//        """.trimIndent()

                // Set the text of the TextView to the concatenated string
                textview.text = "Name: ${holder.firname.text} ${holder.lasname.text}"
                var textview1 = dialog.findViewById<TextView>(R.id.tv4)
                textview1.text = "Age: ${holder.age.text}"
                var textview2 = dialog.findViewById<TextView>(R.id.text1)
                textview2.text = "Email: ${holder.email.text}"
                var textview3 = dialog.findViewById<TextView>(R.id.text3)
                textview3.text = "Company: ${holder.company.text}"
                var textview4 = dialog.findViewById<TextView>(R.id.text5)
                var button = dialog.findViewById<TextView>(R.id.button)
                button.setOnClickListener {
                    dialog.dismiss()
                }
            }

            dialog.show()
        }
        }
    }


