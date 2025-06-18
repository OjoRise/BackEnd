package com.uplus.ojorise.mapper;

import com.uplus.ojorise.domain.Question;
import com.uplus.ojorise.domain.TongBTI;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TongBTIMapper {
    TongBTI getResult(@Param("id") Long id);

    Long findTongIdByName(@Param("tongName") String tongName);

    void insertTongBTIResult(@Param("userId") Long userId, @Param("tongId") Long tongId);
}
