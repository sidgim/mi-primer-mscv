package org.glara.springcloud.msvc.users.services;

import org.glara.springcloud.msvc.users.models.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> listUser();
    List<User> listAllByIds(Iterable<UUID> ids);
    User findUserById(UUID id) ;
    User saveUser(User user);
    User updateUser(UUID id, User user);
    void deleteUserById(UUID id);
    boolean existsByEmail(String email);

}
