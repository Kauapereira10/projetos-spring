package com.kaua.booking_api.service;

import com.kaua.booking_api.dto.service.ServiceRequestDTO;
import com.kaua.booking_api.dto.service.ServiceResponseDTO;
import com.kaua.booking_api.entity.EntityService;
import com.kaua.booking_api.entity.User;
import com.kaua.booking_api.repository.ServiceRepository;
import com.kaua.booking_api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceService {

    private final ServiceRepository serviceRepository;
    private final UserRepository userRepository;

    public ServiceService(ServiceRepository serviceRepository, UserRepository userRepository) {
        this.serviceRepository = serviceRepository;
        this.userRepository = userRepository;
    }


    public ServiceResponseDTO create(ServiceRequestDTO requestDTO) {
        User provider = userRepository.findById(requestDTO.providerId()).orElse(null);

        EntityService service = new EntityService();
        service.setName(requestDTO.name());
        service.setDescription(requestDTO.description());
        service.setPrice(requestDTO.price());
        service.setDurationMinutes(requestDTO.durationMinutes());
        service.setProvider(provider);

        EntityService saved = serviceRepository.save(service);

        return toResponseDTO(saved);

    }

    private ServiceResponseDTO toResponseDTO(EntityService service) {
        return new ServiceResponseDTO(
                service.getId(),
                service.getName(),
                service.getDescription(),
                service.getPrice(),
                service.getDurationMinutes(),
                service.getCreatedAt(),
                service.getProvider().getId(),
                service.getProvider().getName()
        );
    }

}
