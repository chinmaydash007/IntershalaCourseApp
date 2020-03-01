package com.example.intershalatrainingapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.intershalacourseapp.Adapters.BookAdapter
import com.example.intershalacourseapp.Model.Book
import com.example.intershalacourseapp.Model.myBooks
import com.example.intershalacourseapp.R
import kotlinx.android.synthetic.main.chat_fragment.view.*
 
class ChatFragment : Fragment() {
    lateinit var recylerView: RecyclerView
    lateinit var layoutManager: LinearLayoutManager
    lateinit var bookAdapter: BookAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.chat_fragment, container, false)
        recylerView = view.recylerview
        layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recylerView.layoutManager = layoutManager
        bookAdapter = BookAdapter(activity as Context, myBooks.books)
        recylerView.adapter = bookAdapter

        return view
    }



}