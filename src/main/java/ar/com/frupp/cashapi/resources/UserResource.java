package ar.com.frupp.cashapi.resources;

import ar.com.frupp.cashapi.entities.User;
import ar.com.frupp.cashapi.models.UserModel;
import ar.com.frupp.cashapi.services.UserService;
import ar.com.frupp.cashapi.utils.UserMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserResource {

    private UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    @ResponseBody
    public UserModel findUser(@PathVariable("userId") int userId) {
        User user = this.userService.findById(userId);
        return UserMapper.toModel(user);
    }

    @PostMapping
    @ResponseBody
    public UserModel createUser(@RequestBody UserModel request) {
        User saved = this.userService.createUser(request);
        return UserMapper.toModel(saved);
    }
}
