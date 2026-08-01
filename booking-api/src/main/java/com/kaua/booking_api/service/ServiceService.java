package com.kaua.booking_api.service;

import com.kaua.booking_api.enums.UserType;
import com.kaua.booking_api.exeptions.BusinessException;
import com.kaua.booking_api.dto.service.ServiceRequestDTO;
import com.kaua.booking_api.dto.service.ServiceResponseDTO;
import com.kaua.booking_api.entity.EntityService;
import com.kaua.booking_api.entity.User;
import com.kaua.booking_api.repository.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceService {

    private final ServiceRepository repository;
    private final UserService userService;

    public ServiceService(ServiceRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    public void valideProvider(User provider) throws BusinessException {
        if (provider.getUserType() != UserType.PROVIDER) {
            throw new BusinessException("Somente usuários com role PROVIDER podem cadastrar serviços.");
        }
    }

    public ServiceResponseDTO create(ServiceRequestDTO requestDTO) throws BusinessException {
        User provider = userService.findEntityById(requestDTO.providerId());

        valideProvider(provider);

        EntityService service = new EntityService();
        service.setName(requestDTO.name());
        service.setDescription(requestDTO.description());
        service.setPrice(requestDTO.price());
        service.setDurationMinutes(requestDTO.durationMinutes());
        service.setActive(Boolean.TRUE);
        service.setProvider(provider);

        EntityService saved = repository.save(service);

        return toResponseDTO(saved);

    }

    public EntityService findEntityById(Long id) throws BusinessException {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException("Serviço não encontrado com id: " + id));
    }

    public ServiceResponseDTO update(Long id, ServiceRequestDTO requestDTO) throws BusinessException {
        User provider = userService.findEntityById(requestDTO.providerId());

        valideProvider(provider);

        EntityService service = findEntityById(id);
        service.setName(requestDTO.name());
        service.setDescription(requestDTO.description());
        service.setPrice(requestDTO.price());
        service.setDurationMinutes(requestDTO.durationMinutes());

        EntityService saved = repository.save(service);

        return toResponseDTO(saved);

    }

    public ServiceResponseDTO updateProviderService(Long id, ServiceRequestDTO requestDTO) throws BusinessException {
        EntityService service = findEntityById(id);
        User provider = userService.findEntityById(requestDTO.providerId());

        service.setProvider(provider);
        return toResponseDTO(repository.save(service));
    }

    public void delete(Long id) throws BusinessException {
        EntityService service = findEntityById(id);

        repository.deleteById(id);
    }

    public ServiceResponseDTO activate(Long id) throws BusinessException {
        EntityService service = findEntityById(id);
        service.setActive(true);

        EntityService updated = repository.save(service);
        return toResponseDTO(updated);
    }

    public ServiceResponseDTO deactivate(Long id) throws BusinessException {
        EntityService service = findEntityById(id);
        service.setActive(false);

        EntityService updated = repository.save(service);
        return toResponseDTO(updated);
    }

    public List<ServiceResponseDTO> findAll() {
        return repository.findAll().stream().map(this::toResponseDTO).toList();
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
