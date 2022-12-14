package com.example.helloworld.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigDecimal;

public class LoanEntity implements Serializable {

    @SerializedName("id")
    private Long id;
    @SerializedName("borrowerId")
    private Long borrowerId;
    @SerializedName("lenderId")
    private Long lenderId;
    @SerializedName("state")
    private Integer state;
    @SerializedName("money")
    private BigDecimal money;
    @SerializedName("creationTime")
    private Long creationTime;
    @SerializedName("lendingTime")
    private Long lendingTime;
    @SerializedName("refundingTime")
    private Long refundingTime;

    public LoanEntity(Long id, Long borrowerId, Long lenderId, Integer state,
                      BigDecimal money, Long creationTime, Long lendingTime,
                      Long refundingTime) {
        this.id = id;
        this.borrowerId = borrowerId;
        this.lenderId = lenderId;
        this.state = state;
        this.money = money;
        this.creationTime = creationTime;
        this.lendingTime = lendingTime;
        this.refundingTime = refundingTime;
    }

    public LoanEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(Long borrowerId) {
        this.borrowerId = borrowerId;
    }

    public long getLenderId() {
        return lenderId;
    }

    public void setLenderId(Long lenderId) {
        this.lenderId = lenderId;
    }

    public int getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Long creationTime) {
        this.creationTime = creationTime;
    }

    public long getLendingTime() {
        return lendingTime;
    }

    public void setLendingTime(Long lendingTime) {
        this.lendingTime = lendingTime;
    }

    public long getRefundingTime() {
        return refundingTime;
    }

    public void setRefundingTime(Long refundingTime) {
        this.refundingTime = refundingTime;
    }


}


