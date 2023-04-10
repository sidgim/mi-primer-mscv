package org.glara.springcloud.msvc.users.services;

import org.glara.springcloud.msvc.exception.BadRequestException;
import org.glara.springcloud.msvc.exception.NotFoundException;
import org.glara.springcloud.msvc.users.models.entity.User;
import org.glara.springcloud.msvc.users.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;


    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    @Transactional(readOnly = true)
    public List<User> listUser() {
        return (List<User>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserById(UUID id) {
        Optional<User> user = repository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new NotFoundException("User not found for ID: " + id);
        }
    }
    @Override
    @Transactional
    public User saveUser(User user) {
        if(existsByEmail(user.getEmail())) {
            throw new BadRequestException("Email " + user.getEmail() + " has already been created");
        }
        return repository.save(user);
    }

    @Override
    @Transactional
    public User updateUser(UUID id, User user) {
        User oldUser = findUserById(id);
        if(!oldUser.getEmail().equals(user.getEmail()) && existsByEmail(user.getEmail())) {
            throw new BadRequestException("Email " + user.getEmail() + " has already been created");
        }
        user.setId(id);
        return repository.save(user);
    }
    @Override
    @Transactional
    public void deleteUserById(UUID id) {
        findUserById(id);
        repository.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }
}
