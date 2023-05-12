package com.travelproject.travelproject.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.travelproject.travelproject.entity.primaryKey.CommentPk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Comment")
@Table(name = "Comment")
@IdClass(CommentPk.class)
public class CommentEntity {
    
    @Id
    private int questionBoardNumber;
    @Id
    private String writeUserEmail;
    private String commentContent;
    private String writeUserName;
    private String commentWriteTime;
}
