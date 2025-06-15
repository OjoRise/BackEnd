package com.uplus.ojorise.mapper;

import com.uplus.ojorise.domain.TongBTI;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TongBTIMapper {
    TongBTI getResult(@Param("id") Long id);
}
