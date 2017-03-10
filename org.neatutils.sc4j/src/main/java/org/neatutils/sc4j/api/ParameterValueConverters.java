package org.neatutils.sc4j.api;

import java.io.File;

public class ParameterValueConverters {

	public static class StringToFileConverter implements ParameterValueConverter {

		@Override
		public Object convert(final Object rawParameter) {
			String path = String.valueOf(rawParameter);
			File file = new File(path);
			return file;
		}

	}
}
