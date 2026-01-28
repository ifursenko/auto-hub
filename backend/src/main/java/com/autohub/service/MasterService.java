package com.autohub.service;

import com.autohub.api.dto.MasterRequest;
import com.autohub.api.dto.MasterResponse;
import com.autohub.domain.Master;
import com.autohub.domain.ServiceStation;
import com.autohub.domain.User;
import com.autohub.repo.MasterRepository;
import com.autohub.repo.ServiceStationRepository;
import com.autohub.repo.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MasterService {
    private final MasterRepository masterRepository;
    private final ServiceStationRepository stationRepository;
    private final UserRepository userRepository;

    public MasterService(MasterRepository masterRepository,
                         ServiceStationRepository stationRepository,
                         UserRepository userRepository) {
        this.masterRepository = masterRepository;
        this.stationRepository = stationRepository;
        this.userRepository = userRepository;
    }

    public List<MasterResponse> listByStation(Long stationId) {
        return masterRepository.findByStationId(stationId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public MasterResponse addMaster(Long stationId, MasterRequest request) {
        ServiceStation station = stationRepository.findById(stationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Station not found"));
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Master master = new Master();
        master.setStation(station);
        master.setUser(user);
        master.setRating(request.getRating() == null ? 5.0 : request.getRating());
        Master saved = masterRepository.save(master);
        return toResponse(saved);
    }

    public void updateRating(Long masterId, Double rating) {
        Master master = masterRepository.findById(masterId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Master not found"));
        master.setRating(rating);
        masterRepository.save(master);
    }

    private MasterResponse toResponse(Master master) {
        return new MasterResponse(
                master.getId(),
                master.getUser().getId(),
                master.getStation().getId(),
                master.getRating(),
                master.getUser().getName()
        );
    }
}
