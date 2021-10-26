package com.example.headsupdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class databaseSQL : AppCompatActivity() {
    private lateinit var dbHandler: DatabaseHandler


    private lateinit var rvMain: RecyclerView
    private lateinit var rvAdapter: RVAdapter
    private lateinit var etName: EditText
    private lateinit var etTaboo1: EditText
    private lateinit var etTaboo2: EditText
    private lateinit var etTaboo3: EditText
    private lateinit var btAdd: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database_sql)

        dbHandler = DatabaseHandler(this)

        rvMain = findViewById(R.id.rvMain)
        rvAdapter = RVAdapter(dbHandler.getCelebrities())
        rvMain.adapter = rvAdapter
        rvMain.layoutManager = LinearLayoutManager(this)

        etName = findViewById(R.id.etName)
        etTaboo1 = findViewById(R.id.etTaboo1)
        etTaboo2 = findViewById(R.id.etTaboo2)
        etTaboo3 = findViewById(R.id.etTaboo3)
        btAdd = findViewById(R.id.btAdd)
        btAdd.setOnClickListener { addCelebrity() }
    }

    private fun addCelebrity(){
        if(etName.text.isNotBlank() && etTaboo1.text.isNotBlank() && etTaboo2.text.isNotBlank() &&
            etTaboo3.text.isNotBlank()){
          var status =  dbHandler.addCelebrity(etName.text.toString(), etTaboo1.text.toString(), etTaboo2.text.toString(), etTaboo3.text.toString())
            Toast.makeText(this, "Celebrity added$status", Toast.LENGTH_LONG).show()
            rvAdapter.update(dbHandler.getCelebrities())
            etName.text.clear()
            etTaboo1.text.clear()
            etTaboo2.text.clear()
            etTaboo3.text.clear()
            etName.clearFocus()
            etTaboo1.clearFocus()
            etTaboo2.clearFocus()
            etTaboo3.clearFocus()
        }else{
            Toast.makeText(this, "All fields are required", Toast.LENGTH_LONG).show()
        }
    }
}