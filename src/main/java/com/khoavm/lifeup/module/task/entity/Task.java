package com.khoavm.lifeup.module.task.entity;

import com.khoavm.lifeup.module.common.entity.BaseEntity;
import com.khoavm.lifeup.module.task.constant.TaskStatus;
import com.khoavm.lifeup.module.user.entity.User;
import jakarta.persistence.*;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "task", schema = "life_up")
public class Task extends BaseEntity {


    @Column(name = "name", length = Integer.MAX_VALUE)
    private String name;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "is_repeat")
    private Boolean isRepeat;

    @Column(name = "repeat_duration")
    private Integer repeatDuration;

    @Column(name = "repeat_times")
    private Integer repeatTimes;

    @Column(name = "start_time")
    private OffsetDateTime startTime;

    @Column(name = "deadline")
    private OffsetDateTime deadline;

    @Column(name = "importance")
    private Integer importance;

    @Column(name = "difficulty")
    private Integer difficulty;

    @Column(name = "status", length = Integer.MAX_VALUE)
    @Enumerated(EnumType.STRING)
    private TaskStatus status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable=false, updatable=false)
    private User user;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "coin_reward")
    private Integer coinReward;


    public void setIsRepeat(boolean isRepeat){
        if (!isRepeat){
            this.setRepeatTimes(0);
            this.setRepeatDuration(0);
        }
        this.isRepeat = isRepeat;
    }
}


