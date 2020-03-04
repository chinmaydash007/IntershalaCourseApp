package com.example.intershalacourseapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.intershalacourseapp.Model.Book_iD
import com.example.intershalacourseapp.Model.SingleBookRESTModel
import com.example.intershalacourseapp.Services.BookService
import com.example.intershalacourseapp.Services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_book_description.*
import retrofit2.Call
import retrofit2.Response

class BookDescription : AppCompatActivity() {
    var tag = "mytag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_description)

        var book_id_from_intent = intent?.getStringExtra("bookId")
        //progressBarLayout.visibility=View.INVISIBLE

        var bookService: BookService = ServiceBuilder.buildService(BookService::class.java)
        var book_id = if (book_id_from_intent == null) Book_iD() else Book_iD(book_id_from_intent)
        var requestCall = bookService.getBook(book_id)
        requestCall.enqueue(object : retrofit2.Callback<SingleBookRESTModel> {

            override fun onResponse(
                call: Call<SingleBookRESTModel>,
                response: Response<SingleBookRESTModel>
            ) {

                var book = response.body()
                Log.d(tag, book.toString())


                if (book?.success == false) {
                    var alertDialog = AlertDialog.Builder(this@BookDescription)
                    alertDialog.setMessage("Data Not Available")
                    alertDialog.setPositiveButton("Ok", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            finish()
                        }
                    })
                    alertDialog.setCancelable(false)
                    alertDialog.show()

                } else {
                    progressBarLayout.visibility = View.INVISIBLE
                }
            }

            override fun onFailure(call: Call<SingleBookRESTModel>, t: Throwable) {
                Log.d(tag, t.message)
            }

        })

    }
}
