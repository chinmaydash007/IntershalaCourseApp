package com.example.intershalacourseapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.intershalacourseapp.Model.Book
import com.example.intershalacourseapp.R
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.single_cardview_layout.view.*

class BookAdapter(var context: Context, var booklist: List<Book>) :
    RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        var view: View =
            LayoutInflater.from(context).inflate(R.layout.single_cardview_layout, parent, false)
        return BookViewHolder(view)
    }

    override fun getItemCount(): Int {
        return booklist.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.name_textview.text = booklist.get(position).name
        holder.author_textview.text = booklist.get(position).author_name
        holder.price_textview.text = booklist.get(position).price.toString()
        if (booklist.get(position).rating == 0) {
            holder.rating.setImageResource(R.drawable.ic_star_empty)
        } else if (booklist.get(position).rating == 5) {
            holder.rating.setImageResource(R.drawable.ic_star_full)
        } else {
            holder.rating.setImageResource(R.drawable.ic_star_half)
        }

    }

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name_textview = itemView.name
        var author_textview = itemView.author
        var price_textview = itemView.price
        var rating = itemView.rate_imageView
        var book_image: CircleImageView = itemView.profile_image
    }
}

