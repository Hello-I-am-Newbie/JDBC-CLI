package com.danny.app.entitiy;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Notice {
	
	private int id;
	private String title;
	private String writerId; 
	private Date regDate;
	private String content; 
	private int hit;
	private String files;
	

} // end class
