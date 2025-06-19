package com.uplus.ojorise.mapper;

import com.uplus.ojorise.domain.TongBTI;
import com.uplus.ojorise.dto.TongBTIPlanResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TongBTIMapper {
    TongBTI getResult(@Param("id") Long id);

    Long findTongIdByName(@Param("tongName") String tongName);

    void insertTongBTIResult(@Param("userId") Long userId, @Param("tongId") Long tongId);

    TongBTIPlanResponse findTongBTIWithPlan(@Param("tongName") String tongName);
}
