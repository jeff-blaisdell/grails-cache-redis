/* Copyright 2012 SpringSource.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package grails.plugin.cache.web.filter.redis;

import java.io.ByteArrayOutputStream;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.Serializer;
import org.springframework.core.serializer.support.SerializationFailedException;

/**
 * @author Burt Beckwith
 */
public class GrailsSerializingConverter implements Converter<Object, byte[]> {

	private Serializer<Object> serializer;

	/**
	 * Serializes the source object and returns the byte array result.
	 */
	public byte[] convert(Object source) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream(128);
			serializer.serialize(source, baos);
			return baos.toByteArray();
		}
		catch (Throwable t) {
			throw new SerializationFailedException("Failed to serialize object using " +
					serializer.getClass().getSimpleName(), t);
		}
	}

	/**
	 * Dependency injection for the serializer.
	 * @param serializer
	 */
	public void setSerializer(Serializer<Object> serializer) {
		this.serializer = serializer;
	}
}
