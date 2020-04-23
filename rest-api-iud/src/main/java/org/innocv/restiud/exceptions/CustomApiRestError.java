package org.innocv.restiud.exceptions;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class CustomApiRestError {

	private HttpStatus httpStatus;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime localTime;
	private String message;
	private String debugMessage;

	private CustomApiRestError() {
		localTime = LocalDateTime.now();
	}

	public CustomApiRestError(HttpStatus status) {
		this();
		this.httpStatus = status;
	}

	public CustomApiRestError(HttpStatus status, Throwable ex) {
		this();
		this.httpStatus = status;
		this.message = "Unexpected error";
		this.debugMessage = ex.getLocalizedMessage();
	}

	public CustomApiRestError(HttpStatus status, String message, Throwable ex) {
		this();
		this.httpStatus = status;
		this.message = message;
		this.debugMessage = ex.getLocalizedMessage();
	}

}
