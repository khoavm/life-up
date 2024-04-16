package com.khoavm.lifeup.module.task.repository;

import com.khoavm.lifeup.module.task.entity.Task;
import com.khoavm.lifeup.module.task.entity.Task_;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID>, JpaSpecificationExecutor<Task> {

    static Specification<Task> byName(String name) {

        return (root, query, builder) ->
                builder.like( builder.lower(root.get(Task_.name)), "%" + name.toLowerCase() + "%");
    }

    static Specification<Task> byDescription(String description) {
        return (root, query, builder) ->
                builder.like( builder.lower(root.get(Task_.description)), "%" + description.toLowerCase() + "%");
    }



}