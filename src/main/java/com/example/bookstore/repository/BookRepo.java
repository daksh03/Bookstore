package com.example.bookstore.repository;
// Java Program to Illustrate BookRepo File

//import com.example.bookstore.*;
import com.example.bookstore.model.Book;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepo
	extends MongoRepository<Book, Integer> {
}

