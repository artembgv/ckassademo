package ru.artembgv.ckassademo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5904631806037834901L;

	public PersonNotFoundException(int id) {
		super("Пользователь с id=" + id + " не найден");
	}

	public PersonNotFoundException(long inn) {
		super("Пользователь с ИНН=" + inn + " не найден");
	}
}
