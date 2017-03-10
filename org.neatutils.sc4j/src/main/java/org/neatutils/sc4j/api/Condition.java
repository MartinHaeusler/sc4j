package org.neatutils.sc4j.api;

import static com.google.common.base.Preconditions.*;

import org.neatutils.sc4j.api.annotation.IgnoredIf;
import org.neatutils.sc4j.api.annotation.RequiredIf;
import org.neatutils.sc4j.exceptions.UnknownEnumLiteralException;

public class Condition {

	private final String field;
	private final Comparison comparison;
	private final String value;

	public Condition(final RequiredIf requiredIf) {
		checkNotNull(requiredIf, "Precondition violation - argument 'requiredIf' must not be NULL!");
		this.field = requiredIf.field();
		this.comparison = requiredIf.comparison();
		this.value = requiredIf.compareValue();
	}

	public Condition(final IgnoredIf ignoredIf) {
		checkNotNull(ignoredIf, "Precondition violation - argument 'ignoredIf' must not be NULL!");
		this.field = ignoredIf.field();
		this.comparison = ignoredIf.comparison();
		this.value = ignoredIf.compareValue();
	}

	public boolean appliesTo(final AbstractSchemaConf4jConfiguration config) {
		checkNotNull(config, "Precondition violation - argument 'config' must not be NULL!");
		ParameterMetadata parameter = config.getMetadataOfField(this.field);
		Object value = parameter.getValue(config);
		switch (this.comparison) {
		case IS_SET:
			return value != null;
		case IS_NOT_SET:
			return value == null;
		case IS_SET_TO:
			return this.value.equals(value) || this.value.toString().equals(String.valueOf(value));
		case IS_NOT_SET_TO:
			return this.value.toString().equals(String.valueOf(value)) == false;
		default:
			throw new UnknownEnumLiteralException(this.comparison);
		}
	}
}
