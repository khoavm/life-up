package com.khoavm.lifeup.module.task.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

import java.util.UUID;

@StaticMetamodel(Task.class)
public class Task_ {
    public static volatile SingularAttribute<Task, UUID> id;
    public static volatile SingularAttribute<Task, String> name;
    public static volatile SingularAttribute<Task, String> description;
}
