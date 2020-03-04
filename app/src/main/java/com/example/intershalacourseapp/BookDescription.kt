package com.example.intershalacourseapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.example.intershalacourseapp.Model.Book_iD
import com.example.intershalacourseapp.Model.SingleBookRESTModel
import com.example.intershalacourseapp.Services.BookService
import com.example.intershalacourseapp.Services.ServiceBuilder
import retrofit2.Call
import retrofit2.Response

class BookDescription : AppCompatActivity() {
    var tag = "mytag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_description)

        var bookId = intent?.getStringExtra("102")


        var bookService: BookService = ServiceBuilder.buildService(BookService::class.java)
        var book_id=Book_iD()
        bookId?.let {book_id=Book_iD(bookId) }
        var requestCall = bookService.getBook(book_id)
        requestCall?.enqueue(object : retrofit2.Callback<SingleBookRESTModel> {

            override fun onResponse(call: Call<SingleBookRESTModel>, response: Response<SingleBookRESTModel>) {

                var book = response.body()
                Log.d(tag, book.toString())


                if(book?.success==false){
                    var alertDialog=AlertDialog.Builder(this@BookDescription)
                    alertDialog.setMessage("Data Not Available")
                    alertDialog.setPositiveButton("Ok",object:DialogInterface.OnClickListener{
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            finish()
                        }
                    }).show()

                }
            }

            override fun onFailure(call: Call<SingleBookRESTModel>, t: Throwable) {
                Log.d(tag, t.message)
            }

        })

    }
}