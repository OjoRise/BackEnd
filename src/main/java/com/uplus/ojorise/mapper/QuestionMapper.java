package com.uplus.ojorise.mapper;

import com.uplus.ojorise.domain.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Select("SELECT * FROM question")
    List<Question> findAll();
}
