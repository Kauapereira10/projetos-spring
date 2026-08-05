package com.kaua.booking_api.service;

import com.kaua.booking_api.dto.booking.BookingRequestDTO;
import com.kaua.booking_api.dto.booking.BookingResponseDTO;
import com.kaua.booking_api.entity.Booking;
import com.kaua.booking_api.entity.EntityService;
import com.kaua.booking_api.entity.User;
import com.kaua.booking_api.enums.UserType;
import com.kaua.booking_api.exeptions.BusinessException;
import com.kaua.booking_api.exeptions.ResourceNotFoundException;
import com.kaua.booking_api.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository repository;
    private final ServiceService serviceService;
    private final UserService userService;

    public BookingService(BookingRepository repository, ServiceService serviceService, UserService userService) {
        this.repository = repository;
        this.serviceService = serviceService;
        this.userService = userService;
    }

    public void validateClient(User client) throws BusinessException {
        if (client.getUserType() != UserType.CLIENT) {
            throw new BusinessException("Somente usuários com role CLIENT podem criar ou editar agendamentos.");
        }
    }

    public BookingResponseDTO create(BookingRequestDTO requestDTO) throws BusinessException {
        User client = userService.findEntityById(requestDTO.clientId());
        EntityService service = serviceService.findEntityById(requestDTO.serviceId());

        validateClient(client);

        Booking booking = new Booking();
        booking.setEntityService(service);
        booking.setProvider(service.getProvider());
        booking.setClient(client);
        booking.setNotes(requestDTO.notes());
        booking.setStartTime(requestDTO.startTime());
        booking.setEndTime(requestDTO.startTime().plusMinutes(service.getDurationMinutes()));

        Booking saved = repository.save(booking);

        return toResponseDTO(saved);

    }

    public Booking findEntityById(Long id) throws BusinessException {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Agendamento não encontrado: " + id)
        );
    }

    public BookingResponseDTO update(Long id, BookingRequestDTO requestDTO) throws BusinessException {
        Booking booking = findEntityById(id);
        EntityService service = serviceService.findEntityById(requestDTO.serviceId());

        booking.setEntityService(service);
        booking.setProvider(service.getProvider());
        booking.setNotes(requestDTO.notes());
        booking.setStartTime(requestDTO.startTime());
        booking.setEndTime(requestDTO.startTime().plusMinutes(service.getDurationMinutes()));

        Booking update = repository.save(booking);

        return toResponseDTO(update);
    }

    public void delete(Long id) throws BusinessException {
        Booking booking = findEntityById(id);

        repository.deleteById(id);

    }

    public List<BookingResponseDTO> findAll() {
        return repository.findAll().stream().map(this::toResponseDTO).toList();
    }

    public BookingResponseDTO findById(Long id) throws BusinessException {
        Booking booking = findEntityById(id);

        return toResponseDTO(booking);
    }


    private BookingResponseDTO toResponseDTO(Booking booking) {
        return new BookingResponseDTO(
            booking.getId(),
            booking.getClient().getName(),
            booking.getProvider().getName(),
            booking.getEntityService().getName(),
            booking.getNotes(),
            booking.getCreatedAt(),
            booking.getStartTime(),
            booking.getEndTime(),
            booking.getStatus()
        );
    }

}
