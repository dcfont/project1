package com.revature.services;

import com.revature.models.Reimbursement;
import com.revature.repositories.ReimbDAO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReimbursementServiceTest {
    private ReimbursementService rService;

    private ReimbDAO reimbDAO = Mockito.mock(ReimbDAO.class);

    public ReimbursementServiceTest()
    {
        this.rService = new ReimbursementService(reimbDAO);
    }

    @Test
    void viewTicketsByAuthor() {
        //arrange
        Integer author_id = 2;
        List<Reimbursement> expectedOutput = new ArrayList<>();
        expectedOutput.add(new Reimbursement( 1, 33.54, null, null, null, null, author_id, null, 0, 0, "Food", "Pending"));

        Mockito.when(reimbDAO.viewTicketsByAuthor(author_id)).thenReturn(expectedOutput);

        //act
        List<Reimbursement> actualOutput = rService.viewTicketsByAuthor(author_id);

        //assert
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void viewTicketById() {
        //arrange
        Integer id = 2;
        Reimbursement expectedOutput = (new Reimbursement( id, 33.54, null, null, null, null, 2, null, 0, 0, "Food", "Pending"));

        Mockito.when(reimbDAO.viewTicketsById(id)).thenReturn(expectedOutput);

        //act
        Reimbursement actualOutput = rService.viewTicketsById(id);

        //assert
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void addReimbRequestSimple() {
        //arrange
        Double amount = 65.47;
        Integer author_fk = 2;
        Integer type_fk = 2;

        //act
        rService.addReimbRequestSimple(amount, author_fk, type_fk);

        //assert
        Mockito.verify(reimbDAO).addReimbRequestSimple(amount, author_fk, type_fk);
    }

    @Test
    void approveRequest() {
        //arrange
        Integer resolver_fk = 1;
        Integer id = 1;

        //act
        rService.approveRequest(resolver_fk,id);

        //assert
        Mockito.verify(reimbDAO).approveRequest(1, 1);
    }

    @Test
    void denyRequest() {
        //arrange
        Integer resolver_fk = 1;
        Integer id = 1;

        //act
        rService.denyRequest(resolver_fk,id);

        //assert
        Mockito.verify(reimbDAO).denyRequest(1, 1);
    }

    @Test
    void viewAllReimbAllEmployees() {
        //arrange
        List<Reimbursement> expectedOutput = new ArrayList<>();
        expectedOutput.add(new Reimbursement( 1, 33.54, null, null, null, null, 1, null, 0, 0, "Food", "Pending"));

        Mockito.when(reimbDAO.viewAllReimbAllEmployees()).thenReturn(expectedOutput);

        //act
        List<Reimbursement> actualOutput = rService.viewAllReimbAllEmployees();

        //assert
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void viewAllReimbNotSelf() {
        //arrange
        Integer author_id = 2;
        List<Reimbursement> expectedOutput = new ArrayList<>();
        expectedOutput.add(new Reimbursement( 1, 33.54, null, null, null, null, 4, null, 0, 0, "Food", "Pending"));
        expectedOutput.add(new Reimbursement( 1, 33.54, null, null, null, null, author_id, null, 0, 0, "Food", "Pending"));


        Mockito.when(reimbDAO.viewAllReimbNotSelf(author_id)).thenReturn(expectedOutput);

        //act
        List<Reimbursement> actualOutput = rService.viewAllReimbNotSelf(author_id);

        //assert
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void filterByStatusNotSelf() {
        //arrange
        Integer author_id = 2;
        List<Reimbursement> expectedOutput = new ArrayList<>();
        expectedOutput.add(new Reimbursement( 1, 33.54, null, null, null, null, 5, null, 0, 0, "Food", "Pending"));

        Mockito.when(reimbDAO.filterByStatusNotSelf(author_id)).thenReturn(expectedOutput);

        //act
        List<Reimbursement> actualOutput = rService.filterByStatusNotSelf(author_id);

        //assert
        assertEquals(expectedOutput, actualOutput);
    }
}