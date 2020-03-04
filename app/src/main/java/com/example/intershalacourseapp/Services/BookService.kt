package com.example.intershalacourseapp.Services

import com.example.intershalacourseapp.Model.BookRESTmodel
import com.example.intershalacourseapp.Model.Book_iD
import com.example.intershalacourseapp.Model.SingleBookRESTModel
import retrofit2.Call
import retrofit2.http.*

interface BookService {
    @Headers("token:05dba0d07f3874", "Content-Type:application/json")
    @GET("fetch_books")
    fun getBookList(): Call<BookRESTmodel>


    @Headers("token:05dba0d07f3874", "Content-Type:application/json")
    @POST("get_book")
    fun getBook(@Body body:Book_iD): Call<SingleBookRESTModel>

}