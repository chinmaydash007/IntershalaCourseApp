package com.example.intershalacourseapp.Services

import com.example.intershalacourseapp.Model.BookRESTmodel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Url

interface BookService {
    @Headers("token:05dba0d07f3874","Content-Type:application/json")
    @GET("fetch_books")
    fun getBookList(): Call<BookRESTmodel>

}