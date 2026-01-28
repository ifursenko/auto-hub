package com.autohub.service;

import com.autohub.api.dto.ServiceRequest;
import com.autohub.api.dto.ServiceResponse;
import com.autohub.domain.ServiceEntity;
import com.autohub.repo.ServiceRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ServiceCatalogService {
    private final ServiceRepository serviceRepository;

    public ServiceCatalogService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<ServiceResponse> listServices() {
        return serviceRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ServiceResponse getService(Long id) {
        ServiceEntity service = serviceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Service not found"));
        return toResponse(service);
    }

    public ServiceResponse create(ServiceRequest request) {
        ServiceEntity service = new ServiceEntity();
        service.setName(request.getName());
        service.setDescription(request.getDescription());
        service.setDurationMinutes(request.getDurationMinutes());
        service.setBasePrice(request.getBasePrice());
        return toResponse(serviceRepository.save(service));
    }

    public ServiceResponse update(Long id, ServiceRequest request) {
        ServiceEntity service = serviceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Service not found"));
        service.setName(request.getName());
        service.setDescription(request.getDescription());
        service.setDurationMinutes(request.getDurationMinutes());
        service.setBasePrice(request.getBasePrice());
        return toResponse(serviceRepository.save(service));
    }

    public void delete(Long id) {
        if (!serviceRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Service not found");
        }
        serviceRepository.deleteById(id);
    }

    private ServiceResponse toResponse(ServiceEntity service) {
        return new ServiceResponse(
                service.getId(),
                service.getName(),
                service.getDescription(),
                service.getDurationMinutes(),
                service.getBasePrice()
        );
    }
}
