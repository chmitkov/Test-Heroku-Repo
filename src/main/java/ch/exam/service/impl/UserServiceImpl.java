package ch.exam.service.impl;

import ch.exam.model.entity.User;
import ch.exam.model.service.UserServiceModel;
import ch.exam.repository.UserRepository;
import ch.exam.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserServiceModel register(UserServiceModel userServiceModel) {
        return this.modelMapper
                .map(this.userRepository
                        .saveAndFlush(this.modelMapper
                                .map(userServiceModel, User.class)), UserServiceModel.class);

    }

    @Override
    public UserServiceModel findByUsername(String username) {
        return this.userRepository
                .findByUsername(username)
                .map(user -> this.modelMapper.map(user, UserServiceModel.class))
                .orElse(null);

    }
}
