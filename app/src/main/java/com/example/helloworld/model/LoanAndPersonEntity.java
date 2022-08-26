package com.example.helloworld.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoanAndPersonEntity implements Serializable {
    @SerializedName("loan")
    private LoanEntity loan;
    @SerializedName("person")
    private PersonEntity person;

    public LoanAndPersonEntity(LoanEntity loan, PersonEntity person) {
        this.loan = loan;
        this.person = person;
    }

    public LoanEntity getLoan() {
        return loan;
    }

    public void setLoan(LoanEntity loan) {
        this.loan = loan;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }
}

