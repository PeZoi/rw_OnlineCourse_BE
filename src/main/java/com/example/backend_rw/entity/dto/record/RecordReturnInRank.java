package com.example.backend_rw.entity.dto.record;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecordReturnInRank {

    private String username;

    @JsonProperty("avatar_user")
    private String avatarUser;

    @JsonProperty("joined_at")
    private Date joinedAt;

    private float grade;

    private int period;

}
