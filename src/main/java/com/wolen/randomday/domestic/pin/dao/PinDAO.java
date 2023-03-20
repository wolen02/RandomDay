package com.wolen.randomday.domestic.pin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.wolen.randomday.domestic.pin.model.PinPlace;

@Repository
public interface PinDAO {
	
	
	public int insertPinPlace(@Param("userId") int userId, @Param("menuId") int menuId);
	
	
	public int deletePinPlace(@Param("userId") int userId, @Param("menuId") int menuId);
	
	
	public List<PinPlace> selectPlacesById(@Param("userId") int userId);

}
