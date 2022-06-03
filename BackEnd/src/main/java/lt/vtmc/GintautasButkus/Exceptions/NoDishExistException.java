package lt.vtmc.GintautasButkus.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoDishExistException extends NullPointerException {
	private static final long serialVersionUID = 1L;
	
	public NoDishExistException(String message) {
		super(message);
	}
}