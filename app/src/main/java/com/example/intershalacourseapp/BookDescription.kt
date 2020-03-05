package com.example.intershalacourseapp

import android.content.Context
import android.content.DialogInterface
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.intershalacourseapp.Database.BookDatabase
import com.example.intershalacourseapp.Database.BookEntities
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
                    progressBarLayout.visibility = View.GONE

                    var BookData = book?.book_data
                    BookData?.let {
                        book_author.text = it.author
                        book_title.text = it.name
                        book_price.text = it.price
                        book_description.text = it.description
                        Glide.with(this@BookDescription).load(it.image).into(book_image)
                        book_rating.text=it.rating

                    }

                }
            }

            override fun onFailure(call: Call<SingleBookRESTModel>, t: Throwable) {
                Log.d(tag, t.message)
            }

        })

    }

    class DBAsynTask(var context: Context, val bookEntities: BookEntities, val mode: Int) :
        AsyncTask<Void, Void, Boolean>() {

        val db = Room.databaseBuilder(context, BookDatabase::class.java, "book-db").build()

        override fun doInBackground(vararg params: Void?): Boolean {
            when (mode) {
                1 -> {
                    //Check DB if the book is favorite or not
                    val book: BookEntities? =
                        db.bookDao().getBookId(bookEntities.book_id.toString())
                    db.close()
                    return book != null
                }
                2 -> {
                    //Save the book into DB as favorite
                    db.bookDao().insertBook(bookEntities)
                    db.close()
                    return true

                }
                3 -> {
                    //Remove the favorite book.
                    db.bookDao().deleteBook(bookEntities)
                    db.close()
                    return true

                }
            }
            return false
        }

    }
}
