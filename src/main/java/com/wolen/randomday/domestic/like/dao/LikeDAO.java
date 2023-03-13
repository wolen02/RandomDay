package com.wolen.randomday.domestic.like.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeDAO {

	// 검색한 후 장소가 한정되어 있어서 장소의 이름으로 가능하겠지만 다른 프로젝트를 할떄에는 다른 방법을
	// 찾아야 하고, 검색결과가 중복으로 나오는 경우가 있는데 이러한 경우에는 하나를 좋아요 누르면 같은 장소
	// 는 좋아요가 유지된 상태로 나오는 기능을 만들어야 할듯
	
	// 좋아요 기능
	
	public int like(@Param("userId") int userId, @Param("placeId") int placeId);
	
	
	// 좋아요 취소 기능
	
	public int unlike(@Param("userId") int userId, @Param("placeId") int placeId);
	
	
	// 좋아요 존재여부 확인 기능
	public int isLike(@Param("userId") int userId, @Param("placeId") int placeId);
	
}
