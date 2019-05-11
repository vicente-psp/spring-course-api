package com.springcourse.resource;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.dto.RequestSaveDto;
import com.springcourse.dto.RequestUpdateDto;
import com.springcourse.dto.RequestUpdateStateDto;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
import com.springcourse.service.RequestService;
import com.springcourse.service.RequestStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "requests")
public class RequestResource {

    @Autowired private RequestService requestService;
    @Autowired private RequestStageService requestStageService;

    @PostMapping
    public ResponseEntity<Request> save(@RequestBody @Valid RequestSaveDto requestSaveDto) {
        Request request = requestSaveDto.transformToRequest();
        Request requestCreated = requestService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(requestCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Request> update(@PathVariable Long id, @RequestBody @Valid RequestUpdateDto requestUpdateDto) {
        Request request = requestUpdateDto.transformToRequest();
        request.setId(id);
        Request requestUpdated = requestService.update(request);
        return ResponseEntity.ok(requestUpdated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Request> getById(@PathVariable Long id) {
        Request request = requestService.getById(id);
        return ResponseEntity.ok(request);
    }

    @GetMapping
    public ResponseEntity<PageModel<Request>> listAll(
            @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size) {
        PageRequestModel pageRequestModel = new PageRequestModel(page, size);
        PageModel<Request> requestPageModel = requestService.listAllOnLazyMode(pageRequestModel);

        return ResponseEntity.ok(requestPageModel);
    }

    @GetMapping("/{id}/request-stages")
    public ResponseEntity<PageModel<RequestStage>> listAllRequestStageByRequestId(@PathVariable Long id,
                          @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size) {
        PageRequestModel pageRequestModel = new PageRequestModel(page, size);

        PageModel<RequestStage> pageModel = requestStageService.listAllByRequestIdOnLazyMode(id, pageRequestModel);

        return ResponseEntity.ok(pageModel);
    }

    @PatchMapping("/state/{id}")
    public ResponseEntity<?> updateState(@RequestBody @Valid RequestUpdateStateDto requestUpdateStateDto, @PathVariable Long id) {
        Request request = new Request();
        request.setId(id);
        request.setState(requestUpdateStateDto.getState());

        requestService.updateRole(request);

        return ResponseEntity.ok().build();
    }

}
