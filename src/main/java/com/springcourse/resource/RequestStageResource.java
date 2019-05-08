package com.springcourse.resource;

import com.springcourse.domain.RequestStage;
import com.springcourse.service.RequestService;
import com.springcourse.service.RequestStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(name = "request-stage")
public class RequestStageResource {

    @Autowired private RequestStageService requestStageService;

    @PostMapping
    public ResponseEntity<RequestStage> save(RequestStage requestStage) {
        RequestStage requestStageCreated = requestStageService.save(requestStage);
        return ResponseEntity.status(HttpStatus.CREATED).body(requestStageCreated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestStage> getById(@PathVariable Long id) {
        RequestStage requestStage = requestStageService.getById(id);
        return ResponseEntity.ok(requestStage);
    }

}
