package com.example.listviewexample01.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.listviewexample01.R
import java.text.FieldPosition

class MySimpleArrayAdapter(context: Context, resource: Int, objects: MutableList<String>) :
    ArrayAdapter<String>(context, resource, objects) {
    var mContext: Context
    var mValues: MutableList<String>
    var mResource: Int

    init {
        mContext = context
        mValues = objects
        mResource = resource
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(mContext)
        val rowView = inflater.inflate(mResource, parent, false)

        val textView = rowView.findViewById<TextView>(R.id.textView2)

        val value = mValues[position]

        textView.text = value

        return rowView
    }
}



