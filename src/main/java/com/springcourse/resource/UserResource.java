package com.springcourse.resource;

import com.springcourse.domain.Request;
import com.springcourse.domain.User;
import com.springcourse.dto.UserLoginDto;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
import com.springcourse.service.RequestService;
import com.springcourse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "users")
public class UserResource {

    @Autowired private UserService userService;
    @Autowired private RequestService requestService;

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        User userCreated = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        User userUpdated = userService.update(user);
        return ResponseEntity.ok(userUpdated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        User user = userService.getById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<PageModel<User>> listAll(@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
        PageRequestModel pageRequestModel = new PageRequestModel(page, size);
        PageModel<User> userPageModel = userService.listAllOnLazyMode(pageRequestModel);

        return ResponseEntity.ok(userPageModel);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody UserLoginDto userLoginDto) {
        User loggedUser = userService.login(userLoginDto.getEmail(), userLoginDto.getPassword());
        return ResponseEntity.ok(loggedUser);
    }

    @GetMapping("/{id}/requests")
    public ResponseEntity<PageModel<Request>> listAllRequestByUserId(@PathVariable Long id,
                     @RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
        PageRequestModel pageRequestModel = new PageRequestModel(page, size);

        PageModel<Request> requestPageModel = requestService.listAllByOwnerIdOnLazyMode(id, pageRequestModel);

        return ResponseEntity.ok(requestPageModel);
    }

}
