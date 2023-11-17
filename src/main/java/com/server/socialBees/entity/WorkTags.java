//package com.server.socialBees.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name="workTags")
//public class WorkTags {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @ManyToOne
//    @JoinColumn(name = "work_id")
//    private Work work;
//
//    @ManyToOne
//    @JoinColumn(name = "tag_id")
//    private Tag tag;
//}
