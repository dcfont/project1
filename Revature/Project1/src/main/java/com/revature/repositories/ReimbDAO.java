package com.revature.repositories;

import com.revature.models.Reimbursement;

import java.util.List;

public interface ReimbDAO {

    List<Reimbursement> viewAllReimb = null;
    public List<Reimbursement> viewTicketsByAuthor(Integer author_fk);
    public Reimbursement viewTicketsById(Integer id);
    public void addReimbRequestSimple(Double amount, Integer author_fk, Integer type_fk);
    public void approveRequest(Integer resolver_fk, Integer id);
    public void denyRequest(Integer resolver_fk, Integer id);
    public List<Reimbursement>  viewAllReimbAllEmployees();
    public List<Reimbursement>  viewAllReimbNotSelf(Integer author_fk);
    public List<Reimbursement>  filterByStatusNotSelf(Integer author_fk);
    public List<Reimbursement>  filterByIdNotSelf(Integer author_fk);


}
