package com.example.helloworld.dto;

import com.example.helloworld.model.LoanEntity;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class LoanDTO implements Serializable {
    @SerializedName("status")
    private String status;
    @SerializedName("loanList")
    private List<LoanEntity> loanList;


    public LoanDTO(String status, List<LoanEntity> loanList) {
        this.status = status;
        this.loanList = loanList;

    }

    public List<LoanEntity> getLoanList() {
        return loanList;
    }

    public void setLoanList(List<LoanEntity> loanList) {
        this.loanList = loanList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

