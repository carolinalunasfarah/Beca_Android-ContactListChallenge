package com.example.contactlistexample

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contactlistexample.adapter.ContactAdapter
import com.example.contactlistexample.data.Contact
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ContactAdapter
    private val contactList = mutableListOf<Contact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Form elements find
        val etName = findViewById<EditText>(R.id.etName)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        val cbAvailable = findViewById<CheckBox>(R.id.cbAvailable)

        val textView = findViewById<TextView>(R.id.textView)
        val btnAdd = findViewById<Button>(R.id.addButton)

        // Set on click listener for add button
        btnAdd.setOnClickListener {
            val name = etName.text.toString()
            val phone = etPhone.text.toString()
            val isAvailable = cbAvailable.isChecked

            // Validations
            if(name.isNotEmpty() || phone.isNotEmpty()){
                textView.text = "Welcome $name"
                textView.visibility = TextView.VISIBLE
            } else if (name.isNotEmpty() || phone.isNotEmpty() || !isAvailable) {
                textView.text = "All fields are required"
                textView.visibility = TextView.VISIBLE
            } else {
                textView.visibility = TextView.INVISIBLE
            }
        }
    }

    // RecyclerView
    private fun setRecyclerViewAdapter(contactList: List<Contact>) {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        adapter = ContactAdapter(contactList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

}