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
import android.content.Context
import android.view.inputmethod.InputMethodManager

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
        val btnFilter = findViewById<Button>(R.id.buttonFilter)

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

                    // List copy so it doesn't change the original list
                    adapter.updateContacts(ArrayList(contactList))

                    // Clear form
                    etName.text.clear()
                    etPhone.text.clear()
                    cbAvailable.isChecked = false
                    hideKeyboard(etName)
                }
            }
        }

        btnFilter.setOnClickListener {
            // Filter contacts
            val filteredContacts = contactList.filter { it.isAvailable }
            // Update adapter to show filtered contacts
            adapter.updateContacts(filteredContacts)
            textView.visibility = TextView.INVISIBLE
        }
    }

    // RecyclerView
    private fun setRecyclerViewAdapter(contactList: List<Contact>) {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        adapter = ContactAdapter(contactList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    // To avoid focus goes and keyboard appears after adding contact and adding other
    private fun hideKeyboard(editText: EditText) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editText.windowToken, 0)
    }

}