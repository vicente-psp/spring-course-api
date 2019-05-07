package com.springcourse.service;

import com.springcourse.domain.RequestStage;
import com.springcourse.repository.RequestRepository;
import com.springcourse.repository.RequestStageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RequestStageService {
    
    @Autowired private RequestStageRepository requestStageRepository;

    @Autowired private RequestRepository requestRepository;

    public RequestStage save(RequestStage requestStage) {
        requestStage.setRealizationDate(new Date());
        RequestStage requestStageCreated = requestStageRepository.save(requestStage);

        requestRepository.updateStatus(requestStage.getRequest().getId(), requestStage.getRequestState().toString());

        return requestStageCreated;
    }

    public RequestStage update(RequestStage requestStage) {
        RequestStage requestStageUpdated = requestStageRepository.save(requestStage);
        return requestStageUpdated;
    }

    public RequestStage getById(Long id) {
        Optional<RequestStage> requestStageOptional = requestStageRepository.findById(id);
        return requestStageOptional.get();
    }

    public List<RequestStage> listAllByRequestId(Long requestId) {
        List<RequestStage> requestStages = requestStageRepository.findAllByRequestId(requestId);
        return requestStages;
    }
    
}
