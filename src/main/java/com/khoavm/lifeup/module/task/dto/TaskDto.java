package com.khoavm.lifeup.module.task.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.khoavm.lifeup.module.task.constant.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * DTO for {@link com.khoavm.lifeup.module.task.entity.Task}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TaskDto implements Serializable {
    private UUID id;
    private String name;
    private String description;
    private Integer coinReward;
    private Boolean isRepeat;
    private Integer repeatDuration;
    private Integer repeatTimes;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private OffsetDateTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private OffsetDateTime deadline;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private OffsetDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private OffsetDateTime updatedAt;
    private Integer importance;
    private Integer difficulty;
    private TaskStatus status;


    private UUID userId;
}