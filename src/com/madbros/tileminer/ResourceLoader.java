package com.madbros.tileminer;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

final public class ResourceLoader {
	public static InputStream load(String path) {
		InputStream input = ResourceLoader.class.getResourceAsStream(path);
		if (input == null) {
			input = ResourceLoader.class.getResourceAsStream("/"+path);
		}
		return input;
	}
}
