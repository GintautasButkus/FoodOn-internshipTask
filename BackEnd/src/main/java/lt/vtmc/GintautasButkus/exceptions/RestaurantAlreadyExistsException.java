package lt.vtmc.GintautasButkus.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class RestaurantAlreadyExistsException extends IllegalArgumentException {
	private static final long serialVersionUID = 1L;
	
	public RestaurantAlreadyExistsException(String message) {
		super(message);
	}
}
