package ru.artembgv.ckassademo.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {

	private HttpStatus status;
	private String message;

	private String detailMessage;

	private List<FieldValidationError> fieldValidationErrors;

	public Error(HttpStatus status, String message, Exception e) {
		this.status = status;
		this.message = message;
		this.detailMessage = e.getLocalizedMessage();
	}

	public Error(HttpStatus httpStatus) {
		this.status = httpStatus;
	}

	void addValidationErrors(List<FieldError> fieldErrors) {
		fieldErrors.forEach(error -> {
			FieldValidationError subError = new FieldValidationError();
			subError.setField(error.getField());
			subError.setMessage(error.getDefaultMessage());
			subError.setRejectedValue(error.getRejectedValue());
			subError.setObject(error.getObjectName());
			this.addSubError(subError);
		});
	}

	private void addSubError(FieldValidationError subError) {
		if (fieldValidationErrors == null) {
			fieldValidationErrors = new ArrayList<>();
		}
		fieldValidationErrors.add(subError);
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetailMessage() {
		return detailMessage;
	}

	public void setDetailMessage(String detailMessage) {
		this.detailMessage = detailMessage;
	}

	public List<FieldValidationError> getFieldValidationErrors() {
		return fieldValidationErrors;
	}

	public void setFieldValidationErrors(List<FieldValidationError> fieldValidationErrors) {
		this.fieldValidationErrors = fieldValidationErrors;
	}
}