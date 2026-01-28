package com.autohub.api;

import com.autohub.api.dto.MasterRequest;
import com.autohub.api.dto.MasterResponse;
import com.autohub.api.dto.StationRequest;
import com.autohub.api.dto.StationResponse;
import com.autohub.service.MasterService;
import com.autohub.service.StationService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stations")
public class StationController {
    private final StationService stationService;
    private final MasterService masterService;

    public StationController(StationService stationService, MasterService masterService) {
        this.stationService = stationService;
        this.masterService = masterService;
    }

    @PostMapping
    public StationResponse create(@Valid @RequestBody StationRequest request) {
        return stationService.createStation(request);
    }

    @GetMapping
    public List<StationResponse> list(@RequestParam(required = false) Long serviceId,
                                      @RequestParam(required = false) Double minRating) {
        return stationService.listStations(serviceId, minRating);
    }

    @GetMapping("/{id}/masters")
    public List<MasterResponse> listMasters(@PathVariable Long id) {
        return masterService.listByStation(id);
    }

    @PostMapping("/{id}/masters")
    public MasterResponse addMaster(@PathVariable Long id, @Valid @RequestBody MasterRequest request) {
        return masterService.addMaster(id, request);
    }
}
