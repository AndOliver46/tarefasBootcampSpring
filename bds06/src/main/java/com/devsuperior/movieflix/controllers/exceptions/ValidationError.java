package com.devsuperior.movieflix.controllers.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;
	
    List<FieldMessage> fields = new ArrayList<>();
    
    public ValidationError() {
    }

	public List<FieldMessage> getFields() {
		return fields;
	}
	
	public void addError(String field, String message) {
		fields.add(new FieldMessage(field, message));
	}
	
}
