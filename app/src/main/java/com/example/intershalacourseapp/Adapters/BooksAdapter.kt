package com.example.intershalacourseapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.intershalacourseapp.Model.Books
import com.example.intershalacourseapp.R
import kotlinx.android.synthetic.main.book_list_single_cardview.view.*

class BooksAdapter(var bookList: List<Books>, var context: Context) :
    RecyclerView.Adapter<BooksAdapter.BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        var view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_list_single_cardview, parent, false)
        return BookViewHolder(view)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        var book: Books = bookList.get(position)
        holder.name_text_view.text = book.name
        holder.author.text = book.author
        holder.rating.text = book.rating
        holder.price.text = book.price
        Glide.with(context).load(book.image).error(R.drawable.ic_person).into(holder.image)
        if (book.rating.toFloat() > 3) {
            holder.rating_image.setImageResource(R.drawable.ic_star_full)
        } else if (book.rating.toFloat() > 0) {
            holder.rating_image.setImageResource(R.drawable.ic_star_half)
        } else {
            holder.rating_image.setImageResource(R.drawable.ic_star_empty)
        }

    }

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name_text_view = itemView.name
        var author = itemView.author
        var rating = itemView.rating_text_view
        var price = itemView.price
        var image = itemView.book_image
        var rating_image = itemView.rate_imageView
    }
}
