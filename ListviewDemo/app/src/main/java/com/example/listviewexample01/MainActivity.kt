package com.example.listviewexample01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.listviewexample01.Adapter.MySimpleArrayAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.testListView1)
        val listView_new = findViewById<ListView>(R.id.testListView_New)

        val values =
            listOf<String>(
                "Luis1",
                "Luis2",
                "Luis3",
                "Luis4",
                "Luis5",
                "Luis6",
                "Luis7",
                "Luis8",
                "Luis9",
                "Luis10",
                "Luis11",
                "Luis12",
                "Luis13",
                "Luis14",
                "Luis15",
                "Luis16",
                "Luis17",
                "Luis18",
                "Luis19",
                "Luis20"
            )

        val values2 =
            mutableListOf<String>(
                "Luis1",
                "Luis2",
                "Luis3",
                "Luis4",
                "Luis5",
                "Luis6",
                "Luis7",
                "Luis8",
                "Luis9",
                "Luis10",
                "Luis11",
                "Luis12",
                "Luis13",
                "Luis14",
                "Luis15",
                "Luis16",
                "Luis17",
                "Luis18",
                "Luis19",
                "Luis20"
            )
        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, values)
        listView.adapter = adapter;

        var newAdapter = MySimpleArrayAdapter(this, R.layout.layout_item, values2)
        listView_new.adapter = newAdapter;
    }
}