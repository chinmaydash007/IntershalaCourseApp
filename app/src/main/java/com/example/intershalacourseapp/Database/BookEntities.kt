package com.example.intershalacourseapp.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntities(
    @PrimaryKey val book_id:Int,
    @ColumnInfo(name = "book_name") var bookName:String,
    @ColumnInfo(name = "book_author")var bookAuthor:String,
    @ColumnInfo(name = "book_price")var bookPrice:String,
    @ColumnInfo(name = "book_rating")var bookRating:String,
    @ColumnInfo(name = "book_desc")var bookDesc:String,
    @ColumnInfo(name = "book_image")var bookImage:String

)