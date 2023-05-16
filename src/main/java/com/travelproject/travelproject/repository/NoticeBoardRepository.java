package com.travelproject.travelproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.travelproject.travelproject.entity.NoticeBoardEntity;

@Repository
public interface NoticeBoardRepository extends JpaRepository<NoticeBoardEntity, Integer>{
    @Query(
        value = 
        "SELECT " +
        "notice_board_number," +
        "notice_board_title," +
        "notice_board_content," +
        "notice_board_write_date " +
        "FROM Noticeboard " +
        "ORDER BY notice_board_write_date DESC, notice_board_number DESC",
        nativeQuery = true
    )
    public List<NoticeBoardEntity> getList();

    //! 공지사항 상세 조회때 필요
    public NoticeBoardEntity findByNoticeBoardNumber(int noticeBoardNumber);

}
