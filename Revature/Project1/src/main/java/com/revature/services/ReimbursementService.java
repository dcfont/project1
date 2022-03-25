package com.revature.services;

import com.revature.models.Reimbursement;
import com.revature.repositories.ReimbDAO;
import com.revature.repositories.ReimbDAOImpl;

import java.util.List;

public class ReimbursementService
{
    private ReimbDAO reimbDAO = new ReimbDAOImpl();

    public ReimbursementService()
        {
            ReimbDAO reimbDAO = new ReimbDAOImpl();
        }

    public ReimbursementService(ReimbDAO reimbDAO)
        {
            this.reimbDAO = reimbDAO;
        }

    public List<Reimbursement> viewTicketsByAuthor(Integer author_fk)
        {
            return this.reimbDAO.viewTicketsByAuthor(author_fk);
        }

    public Reimbursement viewTicketsById(Integer id)
        {
            return this.reimbDAO.viewTicketsById(id);
        }

    public void addReimbRequestSimple(Double amount, Integer author_fk, Integer type_fk)
        {
            this.reimbDAO.addReimbRequestSimple(amount, author_fk, type_fk);
        }
    public void approveRequest(Integer resolver_fk, Integer id)
        {
            this.reimbDAO.approveRequest(resolver_fk, id);
        }

    public void denyRequest(Integer resolver_fk, Integer id)
        {
            this.reimbDAO.denyRequest(resolver_fk, id);
        }

    public List<Reimbursement>  viewAllReimbAllEmployees()
        {
            return this.reimbDAO.viewAllReimbAllEmployees();
        }

    public List<Reimbursement>  viewAllReimbNotSelf(Integer author_fk)
        {
            return this.reimbDAO.viewAllReimbNotSelf(author_fk);
        }

    public List<Reimbursement>  filterByStatusNotSelf(Integer author_fk)
        {
            return this.reimbDAO.filterByStatusNotSelf(author_fk);
        }
    public List<Reimbursement>  filterByIdNotSelf(Integer author_fk)
    {
        return this.reimbDAO.filterByIdNotSelf(author_fk);
    }

}
