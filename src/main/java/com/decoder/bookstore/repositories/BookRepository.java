package com.decoder.bookstore.repositories;

import com.decoder.bookstore.models.BookModel;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookModel, UUID> {
}
