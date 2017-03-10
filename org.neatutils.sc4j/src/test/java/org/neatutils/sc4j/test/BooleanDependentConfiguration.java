package org.neatutils.sc4j.test;

import org.neatutils.sc4j.api.AbstractSchemaConf4jConfiguration;
import org.neatutils.sc4j.api.Comparison;
import org.neatutils.sc4j.api.annotation.Namespace;
import org.neatutils.sc4j.api.annotation.Parameter;
import org.neatutils.sc4j.api.annotation.RequiredIf;

@Namespace("org.neatutils.sc4j.test")
public class BooleanDependentConfiguration extends AbstractSchemaConf4jConfiguration {

	@Parameter
	private Boolean bool;

	@Parameter
	@RequiredIf(field = "bool", comparison = Comparison.IS_SET_TO, compareValue = "true")
	private String string;

	public Boolean getBool() {
		return this.bool;
	}

	public String getString() {
		return this.string;
	}

}
