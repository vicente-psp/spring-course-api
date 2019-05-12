package com.springcourse.service;

import com.springcourse.domain.RequestStage;
import com.springcourse.exception.NotFoundException;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
import com.springcourse.repository.RequestRepository;
import com.springcourse.repository.RequestStageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

        requestRepository.updateStatus(requestStage.getRequest().getId(), requestStage.getRequestState());

        return requestStageCreated;
    }

    public RequestStage update(RequestStage requestStage) {
        RequestStage requestStageUpdated = requestStageRepository.save(requestStage);
        return requestStageUpdated;
    }

    public RequestStage getById(Long id) {
        Optional<RequestStage> requestStageOptional = requestStageRepository.findById(id);
        return requestStageOptional.orElseThrow(() -> new NotFoundException("Não existe pedido para o id " + id));
    }

    public List<RequestStage> listAllByRequestId(Long requestId) {
        List<RequestStage> requestStages = requestStageRepository.findAllByRequestId(requestId);
        return requestStages;
    }

    public PageModel<RequestStage> listAllByRequestIdOnLazyMode(Long ownerId, PageRequestModel pageRequestModel) {
        Pageable pageable = PageRequest.of(pageRequestModel.getPage(), pageRequestModel.getSize());

        Page<RequestStage> page = requestStageRepository.findAllByRequestId(ownerId, pageable);

        PageModel<RequestStage> pageModel = new PageModel<>((int) page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());

        return pageModel;
    }
    
}
