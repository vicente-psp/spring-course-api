package com.springcourse.service;

import com.springcourse.domain.Request;
import com.springcourse.domain.enums.RequestState;
import com.springcourse.exception.NotFoundException;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
import com.springcourse.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    @Autowired private RequestRepository requestRepository;

    public Request save(Request request) {
        request.setState(RequestState.OPEN);
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
        return requestOptional.orElseThrow(() -> new NotFoundException("Não existe usuário para o id " + id));
    }

    public List<Request> listAll() {
        List<Request> requests = requestRepository.findAll();
        return requests;
    }

    public PageModel<Request> listAllOnLazyMode(PageRequestModel pageRequestModel) {
        Pageable pageable = PageRequest.of(pageRequestModel.getPage(), pageRequestModel.getSize());
        Page<Request> page = requestRepository.findAll(pageable);

        PageModel<Request> pageModel = new PageModel<>((int) page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());

        return pageModel;
    }

    public List<Request> listAllByOwnerId(Long ownerId) {
        List<Request> requests = requestRepository.findAllByOwnerId(ownerId);
        return requests;
    }

    public PageModel<Request> listAllByOwnerIdOnLazyMode(Long ownerId, PageRequestModel pageRequestModel) {
        Pageable pageable = PageRequest.of(pageRequestModel.getPage(), pageRequestModel.getSize());

        Page<Request> page = requestRepository.findAllByOwnerId(ownerId, pageable);

        PageModel<Request> pageModel = new PageModel<>((int) page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());

        return pageModel;
    }

}
