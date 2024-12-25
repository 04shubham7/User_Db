package com.example.user_db

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.database.user1
import com.example.user_db.databinding.ActivitySignUpBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignUpBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnsignup.setOnClickListener {
            val email = binding.etmail.text.toString()
            val name = binding.etname.text.toString()
            val username = binding.etuname.text.toString()
            val password = binding.etpass.text.toString()

            val user1= user1(name,email,username,password)

            database= FirebaseDatabase.getInstance("https://dbusers-ec263-default-rtdb.firebaseio.com/").getReference("DbU ser")
            database.child(username).setValue(user1).addOnSuccessListener {
                binding.etmail.text?.clear()
                binding.etname.text?.clear()
                binding.etuname.text?.clear()
                binding.etpass.text?.clear()

                Toast.makeText(this@SignUpActivity,"User Successfully Registered", Toast.LENGTH_LONG).show()
            }.addOnFailureListener {
                Toast.makeText(this@SignUpActivity,"Failed", Toast.LENGTH_LONG).show()
            }

        }
        binding.textView2.setOnClickListener {
            val intent= Intent(this@SignUpActivity,SigninActivity::class.java)
            startActivity(intent)
        }
    }
}