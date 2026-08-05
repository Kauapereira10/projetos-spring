package com.kaua.booking_api.service;

import com.kaua.booking_api.enums.UserType;
import com.kaua.booking_api.exeptions.BusinessException;
import com.kaua.booking_api.dto.user.UserRequestDTO;
import com.kaua.booking_api.dto.user.UserResponseDTO;
import com.kaua.booking_api.entity.User;
import com.kaua.booking_api.exeptions.ResourceNotFoundException;
import com.kaua.booking_api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserResponseDTO createUser(UserRequestDTO requestDTO) throws BusinessException {
        if(repository.existsByEmail(requestDTO.email())) {
            throw new BusinessException("Já existe um usuário cadastrado com esse email.");
        }
        User user = new User();
        user.setName(requestDTO.name());
        user.setEmail(requestDTO.email());
        user.setPassword(requestDTO.password());
        user.setPhone(requestDTO.phone());

        user.setUserType(requestDTO.userType());

        User saved = repository.save(user);
        return toResponseDTO(saved);
    }

    public List<UserResponseDTO> findAll() {
        return repository.findAll().stream().map(this::toResponseDTO).toList();
    }

    public UserResponseDTO findByid(Long id) throws BusinessException {
        User user = findEntityById(id);
        return toResponseDTO(user);
    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO requestDTO) throws BusinessException {
        User user = findEntityById(id);

        if(!user.getEmail().equals(requestDTO.email()) && repository.existsByEmail(requestDTO.email())) {
            throw new BusinessException("Já existe um usuário cadastrado com esse email.");
        }

        user.setName(requestDTO.name());
        user.setEmail(requestDTO.email());
        user.setPhone(requestDTO.phone());
        user.setUserType(requestDTO.userType());

        User saved = repository.save(user);
        return toResponseDTO(saved);
    }

    public UserResponseDTO updateUserType(Long id, UserRequestDTO requestDTO) throws BusinessException {
        User user = findEntityById(id);

        user.setUserType(requestDTO.userType());
        User saved = repository.save(user);
        return toResponseDTO(saved);
    }

    public void delete(Long id) throws BusinessException {
        User user = findEntityById(id);
        repository.delete(user);
    }

    public User findEntityById(Long id) throws BusinessException {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com id: " + id));
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
