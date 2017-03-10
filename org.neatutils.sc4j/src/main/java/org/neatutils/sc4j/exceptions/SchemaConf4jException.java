package org.neatutils.sc4j.exceptions;

public class SchemaConf4jException extends RuntimeException {

	public SchemaConf4jException() {
		super();
	}

	protected SchemaConf4jException(final String message, final Throwable cause, final boolean enableSuppression,
			final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SchemaConf4jException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public SchemaConf4jException(final String message) {
		super(message);
	}

	public SchemaConf4jException(final Throwable cause) {
		super(cause);
	}

}
