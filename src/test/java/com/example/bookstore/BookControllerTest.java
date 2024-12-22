package com.example.bookstore;

import com.example.bookstore.controller.Bookcontroller;
import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(Bookcontroller.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepo bookRepo;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAddBook() throws Exception {
        Book book = new Book(1, "Spring Boot", "Daksh");

        mockMvc.perform(post("/addBook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(content().string("Added Successfully"));

        verify(bookRepo, times(1)).save(Mockito.any(Book.class));
    }

    @Test
    public void testGetAllBooks() throws Exception {
        Book book1 = new Book(1, "Spring Boot", "Daksh");
        Book book2 = new Book(2, "Java Basics", "Daksh");

        when(bookRepo.findAll()).thenReturn(Arrays.asList(book1, book2));

        mockMvc.perform(get("/findAllBooks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].bookName").value("Java Basics"));

        verify(bookRepo, times(1)).findAll();
    }

    @Test
    public void testDeleteBook() throws Exception {
        int bookId = 1;
        when(bookRepo.findById(bookId)).thenReturn(Optional.of(new Book()));

        mockMvc.perform(delete("/delete/{id}", bookId))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted Successfully"));

        verify(bookRepo, times(1)).deleteById(bookId);
    }
}
