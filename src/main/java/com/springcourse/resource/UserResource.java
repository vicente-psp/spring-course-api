package com.springcourse.resource;

import com.springcourse.domain.Request;
import com.springcourse.domain.User;
import com.springcourse.dto.UserLoginDto;
import com.springcourse.dto.UserSaveDto;
import com.springcourse.dto.UserUpdateDto;
import com.springcourse.dto.UserUpdateRoleDto;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
import com.springcourse.service.RequestService;
import com.springcourse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "users")
public class UserResource {

    @Autowired private UserService userService;
    @Autowired private RequestService requestService;
    @Autowired private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<User> save(@RequestBody @Valid UserSaveDto userSaveDto) {
        User user = userSaveDto.transformToUser();
        User userCreated = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody @Valid UserUpdateDto userUpdateDto) {
        User user = userUpdateDto.transformToUser();
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
    public ResponseEntity<PageModel<User>> listAll(
            @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size) {
        PageRequestModel pageRequestModel = new PageRequestModel(page, size);
        PageModel<User> userPageModel = userService.listAllOnLazyMode(pageRequestModel);

        return ResponseEntity.ok(userPageModel);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody @Valid UserLoginDto userLoginDto) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id}/requests")
    public ResponseEntity<PageModel<Request>> listAllRequestByUserId(@PathVariable Long id,
                     @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size) {
        PageRequestModel pageRequestModel = new PageRequestModel(page, size);

        PageModel<Request> requestPageModel = requestService.listAllByOwnerIdOnLazyMode(id, pageRequestModel);

        return ResponseEntity.ok(requestPageModel);
    }

    @PatchMapping("/role/{id}")
    public ResponseEntity<?> updateRole(@RequestBody @Valid UserUpdateRoleDto userDto, @PathVariable Long id) {
        User user = new User();
        user.setId(id);
        user.setRole(userDto.getRole());

        userService.updateRole(user);

        return ResponseEntity.ok().build();
    }

}
