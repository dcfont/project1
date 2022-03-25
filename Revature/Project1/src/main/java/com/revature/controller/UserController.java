package com.revature.controller;

import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.models.JsonResponse;
import io.javalin.http.Context;

public class UserController {
    private UserService userService;

    public UserController(){
        this.userService = new UserService();
    }

    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    public void createEmployee(Context context)
    {
        User user = context.bodyAsClass(User.class);

        JsonResponse j;
            if (user.getUser_role_id_fk() == 0) {

                userService.createEmployee(user);

                j = new JsonResponse(true, "employee created", null);

            } else {
                userService.createManager(user);

                j = new JsonResponse(true, "manager created", null);
            }

        context.json(j);
    }


    public void validateCredentials(Context context)
    {
        JsonResponse j;

        User credentials = context.bodyAsClass(User.class);

        User userFromDb = userService.validateCredentials(credentials.getUsername(), credentials.getPassword());

        if(userFromDb == null){
            j = new JsonResponse(false, "invalid username or password", null);
        }else {
            j = new JsonResponse(true, "user correct", userFromDb);
        }

        context.json(j);
    }
}
