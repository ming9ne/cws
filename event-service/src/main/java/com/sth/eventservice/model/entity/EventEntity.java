package com.sth.eventservice.model.entity;

import com.sth.eventservice.model.dto.EventDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventEntity {

    @Column
    private String codename; // 분류
    @Id
    private String guname; // 자치구
    @Column
    private String title; // 공연 행사명
    @Column
    private String date; // 날짜 시간
    @Column
    private String place; // 장소
    @Column
    private String useFee;  // 이용 요금
    @Column
    private String player; // 출연자 정보
    @Column
    private String program; // 프로그램 소개
    @Column
    private String mainImg; // 대표 이미지
    @Column
    private double lot; // 위도 X좌표
    @Column
    private double lat; // 경도 Y좌표


    public EventDTO toDto() {
        return EventDTO.builder()
                .codename(codename)
                .guname(guname)
                .title(title)
                .date(date)
                .place(place)
                .useFee(useFee)
                .player(player)
                .program(program)
                .lot(lot)
                .lat(lat)
                .build();
    }
}
