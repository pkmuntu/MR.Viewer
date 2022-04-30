package com.muntu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LikeDTO {

	private Integer LikeId;
	private Integer movieId;
	private Integer userId;
	private boolean likeFlag;

}
