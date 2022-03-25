package com.revature.models;

import javax.swing.text.Document;
import java.sql.Timestamp;

/**
 * This concrete Reimbursement class can include additional fields that can be used for
 * extended functionality of the ERS application.
 *
 * Example fields:
 * <ul>
 *     <li>Description</li>
 *     <li>Creation Date</li>
 *     <li>Resolution Date</li>
 *     <li>Receipt Image</li>
 * </ul>
 *
 */
public class Reimbursement {
    private Integer id;
    private Double amount;
    private Timestamp submitted;
    private Timestamp resolved;
    private String description;
    private Document receipt;
    private Integer author_fk;
    private Integer resolver_fk;
    private Integer status_fk;
    private Integer type_fk;
    private String type;
    private String status;

    public Reimbursement() {
    }


    public Reimbursement(Integer id, Double amount, Timestamp submitted, Integer author_fk, Integer resolver_fk, String type, String status) {
        this.id = id;
        this.amount = amount;
        this.submitted = submitted;
        this.author_fk = author_fk;
        this.resolver_fk = resolver_fk;
        this.type = type;
        this.status = status;

    }

    public Reimbursement(Integer id, Double amount, Timestamp submitted, Timestamp resolved, String description, Document receipt, Integer author_fk, Integer resolver_fk, Integer status_fk, Integer type_fk, String type, String status) {
        this.id = id;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.receipt = receipt;
        this.author_fk = author_fk;
        this.resolver_fk = resolver_fk;
        this.status_fk = status_fk;
        this.type_fk = type_fk;
        this.type = type;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Timestamp getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Timestamp submitted) {
        this.submitted = submitted;
    }

    public Timestamp getResolved() {
        return resolved;
    }

    public void setResolved(Timestamp resolved) {
        this.resolved = resolved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Document getReceipt() {
        return receipt;
    }

    public void setReceipt(Document receipt) {
        this.receipt = receipt;
    }

    public Integer getAuthor_fk() {
        return author_fk;
    }

    public void setAuthor_fk(Integer author_fk) {
        this.author_fk = author_fk;
    }

    public Integer getResolver_fk() {
        return resolver_fk;
    }

    public void setResolver_fk(Integer resolver_fk) {
        this.resolver_fk = resolver_fk;
    }

    public Integer getStatus_fk() {
        return status_fk;
    }

    public void setStatus_fk(Integer status_fk) {
        this.status_fk = status_fk;
    }

    public Integer getType_fk() {
        return type_fk;
    }

    public void setType_fk(Integer type_fk) {
        this.type_fk = type_fk;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "id=" + id +
                ", amount=" + amount +
                ", submitted=" + submitted +
                ", author_fk=" + author_fk +
                ", resolver_fk=" + resolver_fk +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    /**
     * This includes the minimum parameters needed for the {@link com.revature.models.AbstractReimbursement} class.
     * If other fields are needed, please create additional constructors.
     */
    /*public Reimbursement(int id, Status status, User author, User resolver, double amount) {
        super(id, status, author, resolver, amount);
    }*/
}
