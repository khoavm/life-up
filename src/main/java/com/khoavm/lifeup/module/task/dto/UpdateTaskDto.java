package com.khoavm.lifeup.module.task.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UpdateTaskDto {
    private String name;
    private String description;
    private Integer coinReward;
    private Boolean isRepeat;
    private Integer repeatDuration;
    private Integer repeatTimes;
    private OffsetDateTime startTime;
    private OffsetDateTime deadline;
    private Integer importance;
    private Integer difficulty;
}
