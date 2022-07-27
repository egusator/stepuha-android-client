package com.example.helloworld.dto;

import com.example.helloworld.model.PersonEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PersonDTO {
    @SerializedName("status")
    private String status;
    @SerializedName("personList")
    private List<PersonEntity> personList;



    public PersonDTO(String status, List<PersonEntity> personList) {
        this.status = status;
        this.personList = personList;

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PersonEntity> getPersonList() {
        return personList;
    }

    public void setPersonList(List<PersonEntity> personList) {
        this.personList = personList;
    }
}
