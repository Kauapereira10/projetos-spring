package com.kaua.booking_api.service;

import com.kaua.booking_api.exeptions.BusinessException;
import com.kaua.booking_api.dto.service.ServiceRequestDTO;
import com.kaua.booking_api.dto.service.ServiceResponseDTO;
import com.kaua.booking_api.entity.EntityService;
import com.kaua.booking_api.entity.User;
import com.kaua.booking_api.repository.ServiceRepository;
import com.kaua.booking_api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceService {

    private final ServiceRepository serviceRepository;
    private final UserRepository userRepository;

    public ServiceService(ServiceRepository serviceRepository, UserRepository userRepository) {
        this.serviceRepository = serviceRepository;
        this.userRepository = userRepository;
    }


    public ServiceResponseDTO create(ServiceRequestDTO requestDTO) throws BusinessException {
        User provider = userRepository.findById(requestDTO.providerId()).orElseThrow(
                () -> new BusinessException("Provedor não existe.")
        );

        EntityService service = new EntityService();
        service.setName(requestDTO.name());
        service.setDescription(requestDTO.description());
        service.setPrice(requestDTO.price());
        service.setDurationMinutes(requestDTO.durationMinutes());
        service.setProvider(provider);

        EntityService saved = serviceRepository.save(service);

        return toResponseDTO(saved);

    }

    public ServiceResponseDTO update(Long id, ServiceRequestDTO requestDTO) throws BusinessException {
        User provider = userRepository.findById(requestDTO.providerId()).orElseThrow(
                () -> new BusinessException("Provedor não existe.")
        );

        EntityService service = serviceRepository.findById(id).get();
        service.setName(requestDTO.name());
        service.setDescription(requestDTO.description());
        service.setPrice(requestDTO.price());
        service.setDurationMinutes(requestDTO.durationMinutes());
        service.setProvider(provider);

        EntityService saved = serviceRepository.save(service);

        return toResponseDTO(saved);

    }

    public ServiceResponseDTO updateProviderService(Long id, ServiceRequestDTO requestDTO) throws BusinessException {
        EntityService service = serviceRepository.findById(id).orElseThrow(
                () -> new BusinessException("Service não encontrado para atualizar o Provedor do serviço: " + id)
        );
        User provider = userRepository.findById(requestDTO.providerId()).orElseThrow(
                () -> new BusinessException("Provedor não existe.")
        );
        service.setProvider(provider);
        return toResponseDTO(serviceRepository.save(service));
    }

    public void delete(Long id) throws BusinessException {
        EntityService service = serviceRepository.findById(id).orElseThrow(
                () -> new BusinessException("Serviço não existe.")
        );

        serviceRepository.deleteById(id);

    }

    public List<ServiceResponseDTO> findAll() {
        return serviceRepository.findAll().stream().map(this::toResponseDTO).toList();
    }

    public ServiceResponseDTO findById(Long id) throws BusinessException {
        EntityService service = serviceRepository.findById(id).orElseThrow(
                () -> new BusinessException("Service não encontrado: " + id)
        );

        return toResponseDTO(service);
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
