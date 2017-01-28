package com.blog.api;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Favourite {
	@EmbeddedId
	private FavouriteEmbeddable favItem;
	
	public Favourite() {
	}

	public Favourite(String userId, int postId){
		favItem.setUserId(userId);
		favItem.setPostId(postId);
	}
	
	public FavouriteEmbeddable getFavItem() {
		return favItem;
	}

	public void setFavItem(FavouriteEmbeddable favItem) {
		this.favItem = favItem;
	}	

}
