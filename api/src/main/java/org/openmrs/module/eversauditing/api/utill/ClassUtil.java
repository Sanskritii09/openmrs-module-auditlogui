package org.openmrs.module.eversauditing.api.utill;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.envers.Audited;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.SystemPropertyUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassUtil {
	
	public static List<String> findClassesWithAuditedAnnotation() throws IOException {
		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
		MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourcePatternResolver);
		
		//Search only for Service Classes in DWR package.
		String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + resolveBasePackage("org.openmrs")
		        + "/**/*.class";
		
		List<String> candidateClasses = new ArrayList<String>();
		Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);
		for (Resource resource : resources) {
			if (resource.isReadable()) {
				MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
				if (doesClassContainsAuditedAnnotation(metadataReader)) {
					candidateClasses.add(metadataReader.getClassMetadata().getClassName());
				}
			}
		}
		
		return candidateClasses;
	}
	
	public static String convertObjectToJson(Object obj) {
		Map<String, Object> fieldMap = new HashMap<>();
		Class<?> objClass = obj.getClass();

		while (objClass != null) {
			Field[] fields = objClass.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				try {
					fieldMap.put(field.getName(), field.get(obj));
				} catch (IllegalAccessException e) {
					fieldMap.put(field.getName(), "Could not access");
				}
			}
			objClass = objClass.getSuperclass();  // Move to superclass
		}


		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(fieldMap);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static String resolveBasePackage(String basePackage) {
		return ClassUtils.convertClassNameToResourcePath(SystemPropertyUtils.resolvePlaceholders(basePackage));
	}
	
	/**
	 * For the given class, checks if it contains any {@literal @}Audited annotation (at
	 * method/class level).
	 * 
	 * @param metadataReader
	 * @return true if it finds {@literal @}Audited annotation in the class or any of its methods.
	 */
	private static boolean doesClassContainsAuditedAnnotation(MetadataReader metadataReader) {
		try {
			Class dwrClass = Class.forName(metadataReader.getClassMetadata().getClassName());
			
			if (dwrClass.isAnnotationPresent(Audited.class)) {
				return true;
			}
			
		}
		catch (Throwable e) {}
		return false;
	}
}
