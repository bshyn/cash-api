package ar.com.frupp.cashapi.resources;

import ar.com.frupp.cashapi.models.LoanModel;
import ar.com.frupp.cashapi.models.UserModel;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/users")
public class UserResource {

    @GetMapping("/{userId}")
    @ResponseBody
    public UserModel findUser(@PathVariable("userId") int userId) {
        LoanModel loanModel = new LoanModel("1", 2500, userId);

        return new UserModel(
                userId, "test@app.com.ar", "Pepe",
                "Argento", Collections.singletonList(loanModel)
        );
    }
}
