package com.example.auctionapp.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "rating")
public class Rating extends Resource {

    @Min(1)
    @Max(5)
    private Integer grade;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", updatable = false)
    private UserAccount userAccount;

    public Rating() {
    }

    public Rating(@Min(1) @Max(5) Integer grade, String comment, UserAccount userAccount) {
        this.grade = grade;
        this.comment = comment;
        this.userAccount = userAccount;
    }


}
