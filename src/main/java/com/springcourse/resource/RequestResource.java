package com.springcourse.resource;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
import com.springcourse.service.RequestService;
import com.springcourse.service.RequestStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "requests")
public class RequestResource {

    @Autowired private RequestService requestService;
    @Autowired private RequestStageService requestStageService;

    @PostMapping
    public ResponseEntity<Request> save(@RequestBody Request request) {
        Request requestCreated = requestService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(requestCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Request> update(@PathVariable Long id, @RequestBody Request request) {
        request.setId(id);
        Request requestUpdated = requestService.save(request);
        return ResponseEntity.ok(requestUpdated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Request> getById(@PathVariable Long id) {
        Request request = requestService.getById(id);
        return ResponseEntity.ok(request);
    }

    @GetMapping
    public ResponseEntity<PageModel<Request>> listAll(@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
        PageRequestModel pageRequestModel = new PageRequestModel(page, size);
        PageModel<Request> requestPageModel = requestService.listAllOnLazyMode(pageRequestModel);

        return ResponseEntity.ok(requestPageModel);
    }

    @GetMapping("/{id}/request-stages")
    public ResponseEntity<PageModel<RequestStage>> listAllRequestStageByRequestId(@PathVariable Long id,
                          @RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
        PageRequestModel pageRequestModel = new PageRequestModel(page, size);

        PageModel<RequestStage> pageModel = requestStageService.listAllByRequestIdOnLazyMode(id, pageRequestModel);

        return ResponseEntity.ok(pageModel);
    }

}
