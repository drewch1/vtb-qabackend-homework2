package ru.geekbrains.mini.market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.mini.market.entites.Author;
import ru.geekbrains.mini.market.repositories.AuthorRepository;
import java.util.Optional;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Optional<Author> getOneById(Long id) {
        return authorRepository.findById(id);
    }

    public Optional<Author> getByName(String name) {
        return authorRepository.findByName(name);
    }

    public Author save(Author author) {
        return authorRepository.save(author);
    }
}