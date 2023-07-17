package com.example.demo.domain.query;

import com.example.demo.domain.User;
import lombok.Data;
import lombok.Getter;

@Data
public class UserQuery extends User {

    private Integer ageUpper;

    public void setAgeUpper(Integer ageUpper) {
        this.ageUpper = ageUpper;
    }

    public Integer getAgeUpper() {
        return ageUpper;
    }
}
