package com.server.socialBees.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserStatsDTO {
    private Long numberOfWorks;
    private Long numberOfFollowers;
    private Long numberOfFollowing;
}
