package org.neatutils.sc4j.exceptions;

public class SchemaConf4jConfigurationException extends SchemaConf4jException {

	public SchemaConf4jConfigurationException() {
		super();
	}

	protected SchemaConf4jConfigurationException(final String message, final Throwable cause, final boolean enableSuppression,
			final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SchemaConf4jConfigurationException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public SchemaConf4jConfigurationException(final String message) {
		super(message);
	}

	public SchemaConf4jConfigurationException(final Throwable cause) {
		super(cause);
	}

}
