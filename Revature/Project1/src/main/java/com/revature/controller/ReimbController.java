package com.revature.controller;

import com.revature.models.JsonResponse;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;
import io.javalin.http.Context;

import java.util.List;


public class ReimbController {
    private ReimbursementService reimbService;

    public ReimbController()
    {
        this.reimbService = new ReimbursementService();
    }

    public ReimbController(ReimbursementService reimbService)
    {
        this.reimbService = reimbService;
    }

    public void createReimb(Context context)
    {
        Reimbursement reimb = context.bodyAsClass(Reimbursement.class);

        reimbService.addReimbRequestSimple(reimb.getAmount(),reimb.getAuthor_fk(),reimb.getType_fk());

        context.json(new JsonResponse(true, "reimbursement requested for "+ reimb.getAuthor_fk(), reimb));
    }

    public void  viewAllTicketsEmployee(Context context)
    {
        Integer userId = Integer.parseInt(context.pathParam("userId"));
        List<Reimbursement> lists = reimbService.viewTicketsByAuthor(userId);

        context.json(new JsonResponse(true, "displaying lists for user " +userId, lists));
    }

    public void viewOneTicket(Context context)
    {
        Integer reimbId = Integer.parseInt(context.pathParam("reimbId"));
        Reimbursement reimb = reimbService.viewTicketsById(reimbId);

        context.json(new JsonResponse(true, "displaying ticket " +reimbId, reimb));
    }

    //manager

    public void viewAllReimbAllEmployees(Context context)
    {
        Integer.parseInt((context.pathParam("user_role_id_fk")));
        List<Reimbursement> reimbAllEmployees = reimbService.viewAllReimbAllEmployees();

        context.json(new JsonResponse(true, "displaying all Reimbursements", reimbAllEmployees));

    }

    public void viewAllReimbNotSelf(Context context)
    {
        Integer author_fk = Integer.parseInt(context.pathParam("author_fk"));
        List<Reimbursement> reimbNotSelf = reimbService.viewAllReimbNotSelf(author_fk);

        context.json(new JsonResponse(true, "displaying all Reimbursements that do not belong to "+ author_fk, reimbNotSelf));
    }

    public void filterByStatusNotSelf(Context context)
    {
        Integer author_fk = Integer.parseInt(context.pathParam("author_fk"));
        List<Reimbursement> reimbNotSelf = reimbService.filterByStatusNotSelf(author_fk);

        context.json(new JsonResponse(true, "displaying all Reimbursements that do not belong to "+ author_fk, reimbNotSelf));

    }

    public void filterByIdNotSelf(Context context)
    {
        Integer author_fk = Integer.parseInt(context.pathParam("author_fk"));
        List<Reimbursement> reimbNotSelf = reimbService.filterByIdNotSelf(author_fk);

        context.json(new JsonResponse(true, "displaying all Reimbursements that do not belong to "+ author_fk, reimbNotSelf));

    }

    public void approveRequest(Context context)
    {
        Reimbursement reimb = context.bodyAsClass(Reimbursement.class);

        Integer resolver_fk = Integer.parseInt(context.pathParam("userId"));

        reimbService.approveRequest(resolver_fk, reimb.getId());

            context.json(new JsonResponse(true, "Request "+reimb.getId() +" Approved", null));
    }

    public void denyRequest(Context context)
    {
        Reimbursement reimb = context.bodyAsClass(Reimbursement.class);

        Integer resolver_fk = Integer.parseInt(context.pathParam("userId"));

        reimbService.denyRequest(resolver_fk, reimb.getId());

        context.json(new JsonResponse(true, "Request no."+reimb.getId() +" Denied", null));
    }


}
