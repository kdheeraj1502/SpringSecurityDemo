package springsecurity.demo.SpringSecurityDemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthenticationResponse
{
    public AuthenticationResponse(String token) {
    	this.token = token;
	}

	private String token;

}
