package com.springcourse.service;

import com.springcourse.domain.Request;
import com.springcourse.domain.enums.RequestState;
import com.springcourse.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    @Autowired private RequestRepository requestRepository;

    public Request save(Request request) {
        request.setRequestState(RequestState.OPEN);
        request.setCreationDate(new Date());
        Request requestCreated = requestRepository.save(request);
        return requestCreated;
    }

    public Request update(Request request) {
        Request requestUpdated = requestRepository.save(request);
        return requestUpdated;
    }

    public Request getById(Long id) {
        Optional<Request> requestOptional = requestRepository.findById(id);
        return requestOptional.get();
    }

    public List<Request> listAll() {
        List<Request> requests = requestRepository.findAll();
        return requests;
    }

    public List<Request> listAllByOwnerId(Long ownerId) {
        List<Request> requests = requestRepository.findAllByOwnerId(ownerId);
        return requests;
    }

}
