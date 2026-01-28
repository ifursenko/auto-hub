package com.autohub.service;

import com.autohub.api.dto.StationRequest;
import com.autohub.api.dto.StationResponse;
import com.autohub.domain.ServiceEntity;
import com.autohub.domain.ServiceStation;
import com.autohub.domain.User;
import com.autohub.repo.MasterRepository;
import com.autohub.repo.ServiceRepository;
import com.autohub.repo.ServiceStationRepository;
import com.autohub.repo.UserRepository;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class StationService {
    private final ServiceStationRepository stationRepository;
    private final ServiceRepository serviceRepository;
    private final MasterRepository masterRepository;
    private final UserRepository userRepository;
    private final CurrentUserService currentUserService;

    public StationService(ServiceStationRepository stationRepository,
                          ServiceRepository serviceRepository,
                          MasterRepository masterRepository,
                          UserRepository userRepository,
                          CurrentUserService currentUserService) {
        this.stationRepository = stationRepository;
        this.serviceRepository = serviceRepository;
        this.masterRepository = masterRepository;
        this.userRepository = userRepository;
        this.currentUserService = currentUserService;
    }

    public StationResponse createStation(StationRequest request) {
        User owner = getCurrentUser();
        ServiceStation station = new ServiceStation();
        station.setName(request.getName());
        station.setAddress(request.getAddress());
        station.setLat(request.getLat());
        station.setLng(request.getLng());
        station.setOwner(owner);
        if (request.getServiceIds() != null && !request.getServiceIds().isEmpty()) {
            List<ServiceEntity> services = serviceRepository.findAllById(request.getServiceIds());
            station.setServices(Set.copyOf(services));
        }
        ServiceStation saved = stationRepository.save(station);
        return toResponse(saved);
    }

    public List<StationResponse> listStations(Long serviceId, Double minRating) {
        return stationRepository.findAll()
                .stream()
                .filter(station -> serviceId == null || station.getServices()
                        .stream()
                        .anyMatch(service -> Objects.equals(service.getId(), serviceId)))
                .map(this::toResponse)
                .filter(resp -> minRating == null || resp.getRating() >= minRating)
                .collect(Collectors.toList());
    }

    private StationResponse toResponse(ServiceStation station) {
        List<Long> serviceIds = station.getServices()
                .stream()
                .map(ServiceEntity::getId)
                .collect(Collectors.toList());
        Double rating = masterRepository.findByStationId(station.getId())
                .stream()
                .mapToDouble(master -> master.getRating() == null ? 0.0 : master.getRating())
                .average()
                .orElse(0.0);
        return new StationResponse(
                station.getId(),
                station.getName(),
                station.getAddress(),
                station.getLat(),
                station.getLng(),
                station.getOwner().getId(),
                serviceIds,
                rating
        );
    }

    private User getCurrentUser() {
        Long userId = currentUserService.getUserId();
        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }
}
