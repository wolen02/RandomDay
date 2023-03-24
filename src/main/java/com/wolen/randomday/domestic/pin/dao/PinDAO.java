package com.wolen.randomday.domestic.pin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.wolen.randomday.domestic.pin.model.PinPlace;

@Repository
public interface PinDAO {
	
	public int insertPinPlace(@Param("userId") int userId
				, @Param("placeId") int placeId
				, @Param("doName") String doName
				, @Param("guName") String guName
				, @Param("menuName") String menuName
				, @Param("imagePath") String imagePath
				, @Param("title") String title);
	
	
	public int deletePinPlace(@Param("userId") int userId, @Param("placeId") int placeId);
	
	
	public List<PinPlace> selectPlacesById(@Param("userId") int userId);

}
