package id.co.ogya.rest.simple.response;

import lombok.Data;

@Data
public class ErrorMessageBaseResponse {
	private String indonesian;
	private String english;

	public ErrorMessageBaseResponse() {
	}

	public ErrorMessageBaseResponse(String indonesian, String english) {
		this.indonesian = indonesian;
		this.english = english;
	}
}
