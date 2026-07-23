package com.kaua.booking_api.service;

import com.kaua.booking_api.dto.exeptions.BusinessException;
import com.kaua.booking_api.dto.user.UserRequestDTO;
import com.kaua.booking_api.dto.user.UserResponseDTO;
import com.kaua.booking_api.entity.User;
import com.kaua.booking_api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserResponseDTO createUser(UserRequestDTO requestDTO) {
        if(repository.existsByEmail(requestDTO.email())) {
            throw new RuntimeException("Já existe um usuário cadastrado com esse email.");
        }
        User user = new User();
        user.setName(requestDTO.name());
        user.setEmail(requestDTO.email());
        user.setPassword(requestDTO.password());
        user.setPhone(requestDTO.phone());

        User saved = repository.save(user);
        return toResponseDTO(saved);
    }

    public List<UserResponseDTO> findAll() {
        return repository.findAll().stream().map(this::toResponseDTO).toList();
    }

    private UserResponseDTO findByid(Long id) {
        User user = repository.findById(id).get();
        if(!repository.existsUser(user.getId())){
            throw new RuntimeException("Usuário nao existe.");
        }
        return toResponseDTO(user);
    }

    private UserResponseDTO updateUser(Long id, UserRequestDTO requestDTO) {
        User user = repository.findEntityById(id);

        if(!user.getEmail().equals(requestDTO.email()) && repository.existsByEmail(requestDTO.email())) {
            throw new RuntimeException("Já existe um usuário cadastrado com esse email.");
        }

        user.setName(requestDTO.name());
        user.setEmail(requestDTO.email());
        user.setPhone(requestDTO.phone());
        user.setUserType(requestDTO.userType());

        User saved = repository.save(user);
        return toResponseDTO(saved);
    }

    public void delete(Long id) {
        User user = repository.findEntityById(id);
        repository.delete(user);
    }


    public UserResponseDTO toResponseDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getUserType(),
                user.getCreatedAt());
    }

}
