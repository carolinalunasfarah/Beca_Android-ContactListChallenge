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

        // Initialize adapter
        setRecyclerViewAdapter(contactList)

        // Set on click listener for add button
        btnAdd.setOnClickListener {
            val name = etName.text.toString()
            val phone = etPhone.text.toString()
            val isAvailable = cbAvailable.isChecked

            // Validations. When > infinite if statements
            when {
                name.isEmpty() -> {
                    textView.text = "Name is required"
                    textView.visibility = TextView.VISIBLE
                }

                phone.isEmpty() -> {
                    textView.text = "Phone number is required"
                    textView.visibility = TextView.VISIBLE
                }

                else -> {
                    textView.text = "Contact added: $name"
                    textView.visibility = TextView.VISIBLE

                    // Add new contact
                    val newContact = Contact(name, phone, isAvailable)
                    contactList.add(newContact)

                    // Update
                    adapter.updateContacts(contactList)

                    // Clear form
                    etName.text.clear()
                    etPhone.text.clear()
                    cbAvailable.isChecked = false
                }
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