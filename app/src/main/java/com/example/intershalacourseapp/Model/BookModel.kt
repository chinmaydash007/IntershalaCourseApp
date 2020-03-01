package com.example.intershalacourseapp.Model

data class Book(
    var name: String,
    var author_name: String,
    var price: Int,
    var image: String,
    var rating: Int
)

object myBooks {
    var books = listOf(
        Book("oiwewpqe", "wewqe", 34, "", 0),
        Book("oiwewpqe", "wewqe", 34, "", 1),
        Book("oiwewpqe", "wewqe", 34, "", 5),
        Book("oiwewpqe", "wewqe", 34, "", 2),
        Book("oiwewpqe", "wewqe", 34, "", 3),
        Book("oiwewpqe", "wewqe", 34, "", 1),
        Book("oiwewpqe", "wewqe", 34, "", 1),
        Book("oiwewpqe", "wewqe", 34, "", 1),
        Book("oiwewpqe", "wewqe", 34, "", 1),
        Book("oiwewpqe", "wewqe", 34, "", 1),
        Book("oiwewpqe", "wewqe", 34, "", 1),
        Book("oiwewpqe", "wewqe", 34, "", 1),
        Book("oiwewpqe", "wewqe", 34, "", 1),
        Book("oiwewpqe", "wewqe", 34, "", 1),
        Book("oiwewpqe", "wewqe", 34, "", 1),
        Book("oiwewpqe", "wewqe", 34, "", 1),
        Book("oiwewpqe", "wewqe", 34, "", 1)
    )
}