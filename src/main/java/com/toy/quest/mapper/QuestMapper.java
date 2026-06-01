package com.toy.quest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.toy.quest.domain.Quest;

@Mapper
public interface QuestMapper {

    List<Quest> findAllByUserId(long userId);

    Quest findByIdAndUserId(@Param("id") long id, @Param("userId") long userId);

    void insertQuest(Quest quest);

    void updateComplete(Quest quest);

    void deleteByIdAndUserId(@Param("id") long id, @Param("userId") long userId);

}
