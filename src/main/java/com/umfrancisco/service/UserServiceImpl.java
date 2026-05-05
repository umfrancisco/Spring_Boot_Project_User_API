package com.umfrancisco.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.umfrancisco.controller.UserController;
import com.umfrancisco.exception.DuplicateResourceException;
import com.umfrancisco.exception.ResourceNotFoundException;
import com.umfrancisco.model.User;
import com.umfrancisco.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserRepository userRepository;
    
    public UserServiceImpl(UserRepository userRepository) {
    	this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        log.debug("Fetching all users");
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        log.debug("Fetching user with id: {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByUsername(String username) {
        log.debug("Fetching user with username: {}", username);
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsersByCity(String city) {
        log.debug("Fetching users from city: {}", city);
        return userRepository.findByCity(city);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsersByCountry(String country) {
        log.debug("Fetching users from country: {}", country);
        return userRepository.findByCountry(country);
    }

    @Override
    @Transactional
    public User createUser(User user) {
        log.debug("Creating new user: {}", user.getUsername());

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new DuplicateResourceException("Username already taken: " + user.getUsername());
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new DuplicateResourceException("Email already registered: " + user.getEmail());
        }

        User saved = userRepository.save(user);
        log.info("User created with id: {}", saved.getId());
        return saved;
    }

    @Override
    @Transactional
    public User updateUser(Long id, User updatedUser) {
        log.debug("Updating user with id: {}", id);

        User existing = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        // Check uniqueness only if values changed
        if (!existing.getUsername().equals(updatedUser.getUsername())
                && userRepository.existsByUsername(updatedUser.getUsername())) {
            throw new DuplicateResourceException("Username already taken: " + updatedUser.getUsername());
        }
        if (!existing.getEmail().equals(updatedUser.getEmail())
                && userRepository.existsByEmail(updatedUser.getEmail())) {
            throw new DuplicateResourceException("Email already registered: " + updatedUser.getEmail());
        }

        existing.setUsername(updatedUser.getUsername());
        existing.setEmail(updatedUser.getEmail());
        existing.setCity(updatedUser.getCity());
        existing.setCountry(updatedUser.getCountry());

        User saved = userRepository.save(existing);
        log.info("User updated with id: {}", saved.getId());
        return saved;
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        log.debug("Deleting user with id: {}", id);

        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }

        userRepository.deleteById(id);
        log.info("User deleted with id: {}", id);
    }
}
