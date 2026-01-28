package com.autohub.api;

import com.autohub.api.dto.ServiceRequest;
import com.autohub.api.dto.ServiceResponse;
import jakarta.validation.Valid;
import com.autohub.service.ServiceCatalogService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/services")
public class ServiceController {
    private final ServiceCatalogService serviceCatalogService;

    public ServiceController(ServiceCatalogService serviceCatalogService) {
        this.serviceCatalogService = serviceCatalogService;
    }

    @GetMapping
    public List<ServiceResponse> listServices() {
        return serviceCatalogService.listServices();
    }

    @GetMapping("/{id}")
    public ServiceResponse getService(@PathVariable Long id) {
        return serviceCatalogService.getService(id);
    }

    @PostMapping
    public ServiceResponse create(@Valid @RequestBody ServiceRequest request) {
        return serviceCatalogService.create(request);
    }

    @PutMapping("/{id}")
    public ServiceResponse update(@PathVariable Long id, @Valid @RequestBody ServiceRequest request) {
        return serviceCatalogService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        serviceCatalogService.delete(id);
    }
}
