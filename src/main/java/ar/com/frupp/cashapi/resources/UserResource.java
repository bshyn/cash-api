package ar.com.frupp.cashapi.resources;

import ar.com.frupp.cashapi.models.UserModel;
import ar.com.frupp.cashapi.services.UserService;
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
        return this.userService.findById(userId);
    }
}
