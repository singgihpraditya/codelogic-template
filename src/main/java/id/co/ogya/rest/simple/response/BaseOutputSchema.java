package id.co.ogya.rest.simple.response;

import java.util.Date;

import lombok.Data;

@Data
public class BaseOutputSchema {

	protected String epoch;

	public BaseOutputSchema() {
	}

	public void setEpochNow() {
		this.epoch = Long.toString(new Date().getTime());
	}
}
