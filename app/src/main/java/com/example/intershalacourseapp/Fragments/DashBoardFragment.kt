package com.example.intershalatrainingapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.intershalacourseapp.Model.Book
import com.example.intershalacourseapp.Model.BookRESTmodel
import com.example.intershalacourseapp.R
import com.example.intershalacourseapp.Services.BookService
import com.example.intershalacourseapp.Services.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashBoardFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.dashboard_fragment, container, false)
        var bookService: BookService = ServiceBuilder.buildService(BookService::class.java)
        var requestCall: Call<BookRESTmodel> = bookService.getBookList()
        requestCall.enqueue(object : Callback<BookRESTmodel> {

            override fun onResponse(call: Call<BookRESTmodel>, response: Response<BookRESTmodel>) {
                if (response.isSuccessful) {
                    var bookRESTmodel: BookRESTmodel? = response.body()
                    Log.d("mytag", bookRESTmodel.toString())



                } else {
                    Log.d("mytag", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<BookRESTmodel>, t: Throwable) {
                Log.d("mytag", t.message)

            }

        })




        return view
    }
}