package id.co.ogya.rest.simple.response;

import lombok.Data;

@Data
public class ErrorSchemaBaseResponse {
	private String errorCode;
	private ErrorMessageBaseResponse errorMessage;

	public ErrorSchemaBaseResponse() {
		this.errorMessage = new ErrorMessageBaseResponse();
	}
}
