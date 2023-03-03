package com.nitin.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	private int user_id;

	@NotEmpty
	@Size(min = 4,message = "Username must be minimum of 4 character !!")
	private String name;

	@Email(message = "Email address not valid !!")
	private String email;

	@NotEmpty
	@Size(min = 3,max = 10, message = "Password must be in between 3-10 chars")
	private String password;

	@NotEmpty
	private String about;
}
