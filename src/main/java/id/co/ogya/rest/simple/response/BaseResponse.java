package id.co.ogya.rest.simple.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class BaseResponse {
	protected ErrorSchemaBaseResponse errorSchema;

	@JsonIgnore
	protected Integer httpStatusCode;

	public BaseResponse() {
		this.errorSchema = new ErrorSchemaBaseResponse();
	}

	public BaseResponse(String errorCode, String errMsgEn, String errMsgIn) {
		if (errorSchema == null) {
			this.errorSchema = new ErrorSchemaBaseResponse();
		}
		this.errorSchema.setErrorCode(errorCode);
		this.errorSchema.setErrorMessage(new ErrorMessageBaseResponse(errMsgIn, errMsgEn));
	}

	public void setResponse(BaseResponse resp) {
		if (errorSchema == null) {
			this.errorSchema = new ErrorSchemaBaseResponse();
		}
		this.errorSchema.setErrorCode(resp.getErrorSchema().getErrorCode());
		this.errorSchema.setErrorMessage(resp.getErrorSchema().getErrorMessage());
		this.setHttpStatusCode(resp.httpStatusCode);
	}

}
