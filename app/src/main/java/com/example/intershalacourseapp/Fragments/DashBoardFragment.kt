package com.example.intershalatrainingapp

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.intershalacourseapp.Adapters.BooksAdapter

import com.example.intershalacourseapp.Model.BookRESTmodel
import com.example.intershalacourseapp.Model.Books
import com.example.intershalacourseapp.R
import com.example.intershalacourseapp.Services.BookService
import com.example.intershalacourseapp.Services.ServiceBuilder
import com.example.intershalacourseapp.util.ConnectionManager
import kotlinx.android.synthetic.main.chat_fragment.*
import kotlinx.android.synthetic.main.dashboard_fragment.*
import kotlinx.android.synthetic.main.dashboard_fragment.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface ErrorCallBack {
    fun OnButtonClick()
}

class DashBoardFragment : Fragment() {
    lateinit var bookList: ArrayList<Books>
    lateinit var recyclerView: RecyclerView
    lateinit var booksAdapter: BooksAdapter
    lateinit var errorCallback: ErrorCallBack
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.dashboard_fragment, container, false)

        bookList = ArrayList()
        recyclerView = view.book_recylerview
        recyclerView.layoutManager = LinearLayoutManager(activity as Context)
        booksAdapter = BooksAdapter(bookList, activity as Context)
        recyclerView.adapter = booksAdapter


        var bookService: BookService = ServiceBuilder.buildService(BookService::class.java)
        var requestCall: Call<BookRESTmodel> = bookService.getBookList()

        if (ConnectionManager().checkConnection(activity as Context)) {
            requestCall.enqueue(object : Callback<BookRESTmodel> {

                override fun onResponse(
                    call: Call<BookRESTmodel>,
                    response: Response<BookRESTmodel>
                ) {
                    if (response.isSuccessful) {
                        var bookRESTmodel: BookRESTmodel? = response.body()

                        bookRESTmodel?.data?.iterator()?.forEach { book ->
                            bookList.add(book)
                        }
//                    bookRESTmodel?.data?.get(0)?.let { bookList.add(it) }
                        booksAdapter.notifyDataSetChanged()


                    } else {
                        Log.d("mytag", response.errorBody().toString())
                    }
                }

                override fun onFailure(call: Call<BookRESTmodel>, t: Throwable) {
                    Log.d("mytag", t.message)
                    var alertDialog = AlertDialog.Builder(activity as Context)
                    alertDialog.setPositiveButton("Try Again" as CharSequence,
                        object : DialogInterface.OnClickListener {
                            override fun onClick(dialog: DialogInterface?, which: Int) {

                            }
                        })
                }

            })
        } else {
            var alertDialog = androidx.appcompat.app.AlertDialog.Builder(activity as Context)
            alertDialog.setTitle("No Internet Connection")
            alertDialog.setMessage("Please connect to internet!")
            alertDialog.setPositiveButton("Go to Settings" as CharSequence,
                object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        var intent = Intent(Settings.ACTION_SETTINGS)
                        startActivity(intent)
                        activity?.finish()


                    }

                })
            alertDialog.setNegativeButton("Exit" as CharSequence,
                object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        ActivityCompat.finishAffinity(activity as Activity)
                    }
                })
            alertDialog.show()
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        errorCallback = activity as ErrorCallBack

    }
}