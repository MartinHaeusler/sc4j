package org.neatutils.sc4j.test;

import static org.junit.Assert.*;

import java.time.DayOfWeek;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.Test;
import org.neatutils.sc4j.api.SchemaConf4j;
import org.neatutils.sc4j.exceptions.SchemaConf4jConfigurationException;
import org.neatutils.sc4j.test.MyConfiguration.MyEnum;

public class SchemaConf4jTest {

	@Test
	public void correctConfiguration1Works() throws ConfigurationException {
		Configuration apacheConfig = new PropertiesConfiguration("myconfiguration_correct1.properties");
		MyConfiguration config = SchemaConf4j.build(apacheConfig, MyConfiguration.class);
		assertEquals("Martin", config.getName());
		assertEquals(47, config.getIntValue());
		assertEquals(DayOfWeek.SUNDAY, config.getDayOfWeek());
	}

	@Test
	public void correctConfiguration2Works() throws ConfigurationException {
		Configuration apacheConfig = new PropertiesConfiguration("myconfiguration_correct2.properties");
		MyConfiguration config = SchemaConf4j.build(apacheConfig, MyConfiguration.class);
		assertEquals("Martin", config.getName());
		assertEquals(47, config.getIntValue());
		assertEquals(DayOfWeek.MONDAY, config.getDayOfWeek());
		assertEquals(true, config.isHangover());
		assertEquals(100.0, config.getMotivation(), 0.1d);
	}

	@Test
	public void valueAliasingWorks() throws ConfigurationException {
		Configuration apacheConfig = new PropertiesConfiguration("myconfiguration_aliasing.properties");
		MyConfiguration config = SchemaConf4j.build(apacheConfig, MyConfiguration.class);
		assertEquals("Martin", config.getName());
		assertEquals(47, config.getIntValue());
		assertEquals(DayOfWeek.MONDAY, config.getDayOfWeek());
		assertEquals(true, config.isHangover());
		assertEquals(100.0, config.getMotivation(), 0.1d);
	}

	@Test
	public void customValueParsingWorks() throws ConfigurationException {
		Configuration apacheConfig = new PropertiesConfiguration("myconfiguration_valueparser.properties");
		MyConfiguration config = SchemaConf4j.build(apacheConfig, MyConfiguration.class);
		assertEquals("Martin", config.getName());
		assertEquals(47, config.getIntValue());
		assertEquals(DayOfWeek.MONDAY, config.getDayOfWeek());
		assertEquals(true, config.isHangover());
		assertEquals(100.0, config.getMotivation(), 0.1d);
		assertEquals("123", config.getCoordinate().getX());
		assertEquals("456", config.getCoordinate().getY());
	}

	@Test
	public void configWithSuperfluousParametersWorks() throws ConfigurationException {
		Configuration apacheConfig = new PropertiesConfiguration("myconfiguration_superfluous1.properties");
		MyConfiguration config = SchemaConf4j.build(apacheConfig, MyConfiguration.class);
		assertEquals("Martin", config.getName());
		assertEquals(47, config.getIntValue());
		assertEquals(DayOfWeek.MONDAY, config.getDayOfWeek());
		assertEquals(true, config.isHangover());
		assertEquals(100.0, config.getMotivation(), 0.1d);
	}

	@Test(expected = SchemaConf4jConfigurationException.class)
	public void missingValuesAreDetectedProperly() throws ConfigurationException {
		Configuration apacheConfig = new PropertiesConfiguration("myconfiguration_error1.properties");
		SchemaConf4j.build(apacheConfig, MyConfiguration.class);
	}

	@Test(expected = SchemaConf4jConfigurationException.class)
	public void wrongEnumValuesAreDetectedProperly() throws ConfigurationException {
		Configuration apacheConfig = new PropertiesConfiguration("myconfiguration_error2.properties");
		SchemaConf4j.build(apacheConfig, MyConfiguration.class);
	}

	@Test
	public void enumFactoryMethodAnnotationWorks() throws ConfigurationException {
		Configuration apacheConfig = new PropertiesConfiguration("myconfiguration_enumFactoryMethod.properties");
		MyConfiguration config = SchemaConf4j.build(apacheConfig, MyConfiguration.class);
		assertEquals(MyEnum.THREE, config.getMyEnum());

	}

	@Test
	public void canDependOnAValueOfTypeBoolean_case1() throws ConfigurationException {
		// case 1: boolean is true, and dependent value is present
		Configuration apacheConfig = new PropertiesConfiguration("booleanDependentConfiguration_correct.properties");
		BooleanDependentConfiguration config = SchemaConf4j.build(apacheConfig, BooleanDependentConfiguration.class);
		assertEquals(true, config.getBool());
		assertEquals("yes", config.getString());
	}

	@Test
	public void canDependOnAValueOfTypeBoolean_case2() throws ConfigurationException {
		// case 2: boolean is false
		Configuration apacheConfig = new PropertiesConfiguration("booleanDependentConfiguration_correct2.properties");
		BooleanDependentConfiguration config = SchemaConf4j.build(apacheConfig, BooleanDependentConfiguration.class);
		assertEquals(false, config.getBool());
	}

	@Test
	public void canDependOnAValueOfTypeBoolean_case3() throws ConfigurationException {
		// case 3: boolean is true, but dependent value is missing
		try {
			Configuration apacheConfig = new PropertiesConfiguration("booleanDependentConfiguration_wrong.properties");
			SchemaConf4j.build(apacheConfig, BooleanDependentConfiguration.class);
			fail();
		} catch (SchemaConf4jConfigurationException expected) {
			// pass
		}
	}

}
