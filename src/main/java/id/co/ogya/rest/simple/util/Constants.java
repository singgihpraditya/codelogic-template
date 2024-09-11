package id.co.ogya.rest.simple.util;

public class Constants {
	public static final String SERVICE_NAME = "RestSimulator";
	public static final String SUCCESS = "Success";
	public static final String FAILED = "Failed";
	
	public static final String DEFAULT_RESPONSE_BODY = "Not Found";
	public static final Integer DEFAULT_RESPONSE_CODE = 404;
	public static final String DEFAULT_CONTENT_TYPE = "application/json";
	
	public static class ErrorCode{
		public static final String ERROR_CODE_SUCCESS = "CUS-0-000";
		public static final String ERROR_CODE_FAILED = "CUS-9-999";
		public static final String ERROR_MESSAGE_SUCCESS_EN = "Success";
		public static final String ERROR_MESSAGE_SUCCESS_IN = "Sukses";
		public static final String ERROR_MESSAGE_NO_DATA = "No Data Found";
		public static final String ERROR_MESSAGE_UNDER_MAINTENANCE = "System Under Maintenance";
		public static final String ERROR_MESSAGE_DATA_ALREADY_EXIST = "Data Already Exist";
	}
	
}
