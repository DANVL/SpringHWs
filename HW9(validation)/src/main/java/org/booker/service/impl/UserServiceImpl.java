package org.booker.service.impl;

import org.booker.models.User;
import org.booker.repository.UserRepository;
import org.booker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {
        if(userRepository.findById(user.getLogin()).isEmpty()){
            userRepository.save(user);
        }
    }
}
