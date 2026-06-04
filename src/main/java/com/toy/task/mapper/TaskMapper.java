package com.toy.task.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.toy.task.domain.Task;

@Mapper
public interface TaskMapper {

    List<Task> findAllByUserId(long userId);

    Task findByIdAndUserId(@Param("id") long id, @Param("userId") long userId);

    void insertTask(Task task);

    void updateComplete(Task task);

    void deleteByIdAndUserId(@Param("id") long id, @Param("userId") long userId);

}
