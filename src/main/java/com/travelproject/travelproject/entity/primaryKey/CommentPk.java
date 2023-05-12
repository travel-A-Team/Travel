package com.travelproject.travelproject.entity.primaryKey;

import java.io.Serializable;

import javax.persistence.Column;

public class CommentPk implements Serializable {
    
    @Column(name = "question_board_number")
    private int questionBoardNumber;
    @Column(name = "write_user_email")
    private String writeUserEmail;

}
