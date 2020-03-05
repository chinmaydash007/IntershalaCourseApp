package com.example.intershalacourseapp.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface BookDao {
    @Insert
    fun insertBook(bookEntities: BookEntities)

    @Delete
    fun deleteBook(bookEntities: BookEntities)

    @Query("SELECT * from books")
    fun getAllBooks(): List<BookEntities>

    @Query("SELECT * from books where book_id=:bookId")
    fun getBookId(bookId: String): BookEntities

}