package com.example.news.service.impl;

import com.example.news.exception.AlreadyExistsException;
import com.example.news.exception.EntityNotFoundException;
import com.example.news.model.User;
import com.example.news.repository.UserRepository;
import com.example.news.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {



    private final UserRepository repository;

    @Override
    public User updateFields(User oldEntity, User newEntity) {
        if (!Objects.equals(oldEntity.getUsername(), newEntity.getUsername()) && existsByUsername(newEntity.getUsername())) {
            throw new AlreadyExistsException(
                    MessageFormat.format("User with username {0} already exists!",  newEntity.getUsername())
            );
        } else if (!Objects.equals(oldEntity.getUsername(), newEntity.getUsername())) {
            oldEntity.setUsername(newEntity.getUsername());
        }

        if (!Objects.equals(oldEntity.getEmail(), newEntity.getEmail()) && existsByEmail(newEntity.getEmail())) {
            throw new AlreadyExistsException(
                    MessageFormat.format("User with email {0} already exists!",  newEntity.getUsername())
            );
        } else if (!Objects.equals(oldEntity.getEmail(), newEntity.getEmail())) {
            oldEntity.setEmail(newEntity.getEmail());
        }

        return oldEntity;
    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("User with username {0} not found!", username)
                ));
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("User with email {0} not found!", email)
                ));
    }

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public User findById(Long userId) {
        return repository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("User with ID {0} not found!", userId)
                ));
    }

    @Override
    public User save(User entity) {
        return repository.save(entity);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional
    public User update(Long id, User entity) {
        var updatedEntity = updateFields(findById(id), entity);

        return repository.save(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }


}
