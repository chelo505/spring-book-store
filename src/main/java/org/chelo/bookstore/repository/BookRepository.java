package org.chelo.bookstore.repository;

import org.chelo.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;


@org.springframework.stereotype.Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
