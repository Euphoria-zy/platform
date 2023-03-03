package com.platformserver.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.platformserver.domain.Device;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeviceDao extends BaseMapper<Device> {
}
