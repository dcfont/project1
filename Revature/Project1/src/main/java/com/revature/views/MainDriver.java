package com.revature.views;

import com.revature.controller.ReimbController;
import com.revature.controller.UserController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class MainDriver {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("/", Location.CLASSPATH);
                }).start(9000);

        UserController userController = new UserController();
            app.post("/user", userController::createEmployee);
            app.post("/login", userController::validateCredentials);

        ReimbController reimbController = new ReimbController();
            app.post("/newrequest", reimbController::createReimb);
            app.get("/{userId}/allpasttickets", reimbController::viewAllTicketsEmployee);
            app.get("/{reimbId}/pastticket", reimbController::viewOneTicket);

            app.get("/{user_role_id_fk}/viewAllSubmissions", reimbController::viewAllReimbAllEmployees);
            app.get("/{author_fk}/{userId}/allReimbursements", reimbController::viewAllReimbNotSelf);
            app.get("/{author_fk}/{userId}/allReimbursementsOrdered", reimbController::filterByStatusNotSelf);
            app.get("/{author_fk}/{userId}/allReimbursementsIdOrdered", reimbController::filterByIdNotSelf);

            app.post("/{userId}/approve", reimbController::approveRequest);
            app.post("/{userId}/deny", reimbController::denyRequest);


    }
}
