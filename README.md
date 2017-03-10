# sc4j
Schema Confguration for Java: Put a Schema on the Configuration files.

Create a schema for your configurations in a Java class by using annotations, and get validation and API for free!

# Example Usage

First, create the schema class:

```java
@Namespace("org.neatutils.sc4j.test")
public class MyConfiguration extends AbstractSchemaConf4jConfiguration {

	@Parameter
	private String name;

	@Parameter(key = "integer", optional = false)
	private int intValue = 42;

	@ValueAlias(alias = "mon", mapTo = "MONDAY")
	@ValueAlias(alias = "tue", mapTo = "TUESDAY")
	@ValueAlias(alias = "wed", mapTo = "WEDNESDAY")
	@ValueAlias(alias = "thu", mapTo = "THURSDAY")
	@ValueAlias(alias = "fri", mapTo = "FRIDAY")
	@ValueAlias(alias = "sat", mapTo = "SATURDAY")
	@ValueAlias(alias = "sun", mapTo = "SUNDAY")
	@Parameter(key = "org.neatutils.sc4j.test.day", optional = true)
	private DayOfWeek dayOfWeek;

	@Parameter
	@IgnoredIf(field = "dayOfWeek", comparison = Comparison.IS_SET_TO, compareValue = "SATURDAY")
	@IgnoredIf(field = "dayOfWeek", comparison = Comparison.IS_SET_TO, compareValue = "SUNDAY")
	private Double motivation;

	@Parameter()
	@RequiredIf(field = "dayOfWeek", comparison = Comparison.IS_SET_TO, compareValue = "MONDAY")
	private Boolean hangover;
  
  // + standard getters & setters
}
```

... then, instantiate it using an existing `Configuration` object from Apache Configuration:

```java
	Configuration apacheConfig = new PropertiesConfiguration("configFile.properties");
	MyConfiguration config = SchemaConf4j.build(apacheConfig, MyConfiguration.class);
```

Not only you will get a nice API for your configuration because of the getters & setters in your configuration class, but you also get
validation that is executed during the `build` method. The results of this validation will either be forwarded to you directly via
`SchemaConf4jConfigurationException`s, or by logging warnings via `SLF4J`.
