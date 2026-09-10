package com.decoder.bookstore.services;

import com.decoder.bookstore.dtos.BookRecordDto;
import com.decoder.bookstore.models.BookModel;
import com.decoder.bookstore.repositories.BookRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final ReviewService reviewService;

    public BookService(BookRepository bookRepository, ReviewService reviewService) {
        this.bookRepository = bookRepository;
        this.reviewService = reviewService;
    }

    public List<BookModel> findAll() {
        return bookRepository.findAll();
    }

    public Optional<BookModel> findById(UUID id) {
        return bookRepository.findById(id);
    }

    public BookModel save(BookRecordDto bookRecordDto) {
        BookModel bookModel = new BookModel();
        BeanUtils.copyProperties(bookRecordDto, bookModel);
        String review = reviewService.generateReview(bookRecordDto.title());
        bookModel.setReview(review);
        return bookRepository.save(bookModel);
    }

    public BookModel update(BookModel bookModel, BookRecordDto bookRecordDto) {
        BeanUtils.copyProperties(bookRecordDto, bookModel);
        return bookRepository.save(bookModel);
    }

    public void delete(BookModel bookModel) {
        bookRepository.delete(bookModel);
    }
}
