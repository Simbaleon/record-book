package com.simbaleon.spring.API;

import com.simbaleon.spring.models.users.User;
import com.simbaleon.spring.models.users.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Tag(name = "Users")
@RolesAllowed(User.ROLE_ADMIN)
@RequestMapping("users/")
public class UserController {
    UserService service;

    @PostMapping
    public User create(@RequestBody @Valid User request) {
        return service.create(request);
    }

    @PutMapping()
    public User edit(@RequestBody @Valid User request) {
        return service.update(request);
    }

    @DeleteMapping()
    public User delete(@RequestBody @Valid User request) {
        return service.delete(request);
    }

    @PutMapping("/role")
    public User setRole(@RequestParam User.Role role, @RequestParam String studentEmail) {
        User student = service.getIfExists(studentEmail);
        student.setRole(role);
        return service.update(student);
    }
}
