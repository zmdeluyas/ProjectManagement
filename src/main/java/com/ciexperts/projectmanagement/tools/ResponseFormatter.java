package com.ciexperts.projectmanagement.tools;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

/**
 * This class implement a list of method for replacing special characters
 * @author Steven Ramirez
 * @version 1.0
 */
public class ResponseFormatter {
	
	private ResponseFormatter(){		
	}
	/**
	 * Replaces html entity (<, >, &, ", ', ñ, Ñ, ~, \, \n) in a string to its corresponding html entity number (&#60;, &#62;, &#38;, &#34;, &#039;, &#241;, &#209;)
	 * @param list the list to be unescaped
	 * @return the unescaped list 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object escapeHTMLInList(Object list) {
		List<Object> l = (List<Object>) list;
		for (Object o: l) {
			try {
				// to handle list of maps
				if (o instanceof Map && o != null) {
					ResponseFormatter.escapeHTMLInMap((Map<Object, Object>) o);
				}else if (o instanceof List && o != null) {
					ResponseFormatter.escapeHTMLInList(o);
				}else {
					Map properties = BeanUtils.describe(o);					
					Iterator i = properties.keySet().iterator();
					while (i.hasNext()) {
						String property = (String) i.next();																		
						if (properties.containsKey(property)) {
							Object j = properties.get(property);							
							Class type = PropertyUtils.getPropertyType(o, property); //added validation to avoid exception encountered when property is of type List
							if(type.getName().equals("java.lang.String")){
								if (j instanceof String && j != null && (j.toString().contains("\"") || j.toString().contains("'") || j.toString().contains(">") || j.toString().contains("<") || j.toString().contains("&") || j.toString().contains("\u00f1") || j.toString().contains("\u00D1") || j.toString().contains("\\") || j.toString().contains("\r\n") || j.toString().contains("\r") || j.toString().contains("\n") || j.toString().contains("\t"))) {									
									BeanUtils.setProperty(o, property, ResponseFormatter.escapeBackslash(ResponseFormatter.replaceTildes(ResponseFormatter.escapeHTML(j.toString()))));								
								}
							}else{
								ResponseFormatter.escapeHTMLInObject(j);
							}
						}
					}
				}
			} catch (Exception e){
				System.out.println(e);
			}
			/*catch (IllegalAccessException e) {
				ExceptionHandler.logException(e);
			} catch (InvocationTargetException e) {
				ExceptionHandler.logException(e);
			} catch (NoSuchMethodException e) {
				ExceptionHandler.logException(e);
			}*/
		}
		
		return l;
	}
	
	/**
	 * Replaces html entity (<, >, &, ", ', ñ, Ñ, ~, \, \n) in a string to its corresponding html entity number (&#60;, &#62;, &#38;, &#34;, &#039;, &#241;, &#209;)
	 * @param object the object to be unescaped
	 * @return the unescaped object 
	 */
	@SuppressWarnings({ "rawtypes" })
	public static Object escapeHTMLInObject(Object object){
		Object obj = object;
			try {
				Map properties = BeanUtils.describe(obj);
				Iterator i = properties.keySet().iterator();
				while (i.hasNext()) {
					String property = (String) i.next();
					if (properties.containsKey(property)) {						
						Object j = properties.get(property);
						Class type = PropertyUtils.getPropertyType(obj, property);
						if (type.getName().equals("java.lang.String") && j instanceof String && j != null && (j.toString().contains("\"") || j.toString().contains("'") || j.toString().contains(">") || j.toString().contains("<") || j.toString().contains("&") || j.toString().contains("\u00f1") || j.toString().contains("\u00D1") || j.toString().contains("\\") || j.toString().contains("\r\n") || j.toString().contains("\n") || j.toString().contains("\t"))) {
							BeanUtils.setProperty(obj, property, ResponseFormatter.escapeBackslash(ResponseFormatter.replaceTildes(ResponseFormatter.escapeHTML(j.toString()))));							
						}
					}
				}
			}  catch (Exception e){
				System.out.println(e);
			}
			/*catch (IllegalAccessException e) {
				ExceptionHandler.logException(e);
			} catch (InvocationTargetException e) {
				ExceptionHandler.logException(e);
			} catch (NoSuchMethodException e) {
				ExceptionHandler.logException(e);
			}*/
		return obj;
	}
	
	/**
	 * Replaces html entity (<, >, &, ", ', ñ, Ñ, ~, \, \n) in a string to its corresponding html entity number (&#60;, &#62;, &#38;, &#34;, &#039;, &#241;, &#209;)
	 * @param param the map to be unescaped
	 * @return the unescaped map 
	 */
	@SuppressWarnings("unchecked")
	public static Map<Object, Object> escapeHTMLInMap(Map<Object, Object> param){
		Map<Object, Object> map = param;
		Iterator<Object> a = map.keySet().iterator();
		while (a.hasNext()){
			String key = a.next().toString();
			Object value = map.get(key);
			if (value instanceof String && value != null && (value.toString().contains("\"") || value.toString().contains("'") || value.toString().contains(">") || value.toString().contains("<") || value.toString().contains("&") || value.toString().contains("\u00f1") || value.toString().contains("\u00D1") || value.toString().contains("\\") || value.toString().contains("\r\n") || value.toString().contains("\n") || value.toString().contains("\t"))) {
				map.put(key, ResponseFormatter.escapeBackslash(ResponseFormatter.replaceTildes(ResponseFormatter.escapeHTML(value.toString()))));
			}if (value instanceof Map && value != null) {
				ResponseFormatter.escapeHTMLInMap((Map<Object, Object>) value);
			}else if (value instanceof List && value != null) {
				ResponseFormatter.escapeHTMLInList(value);
			}else{
				ResponseFormatter.escapeHTMLInObject(value);
			}
		}
		return map;
	}
	
	/**
	 * Replaces backslashes in a string including \n to its corresponding html entity
	 * @param str the String to be replace
	 * @return the escaped string
	 */
	public static String escapeBackslash(String str){
		return str != null ? (str.replaceAll("\\\\", "\\\\\\\\").replaceAll("(\r\n|\n)", "\\\\n").replaceAll("\t", "\\\\t")) : "";
	}
	
	/**
	 * Replace tildes.
	 * @param str the String to be replace
	 * @return the escaped string
	 */
	public static String replaceTildes(String str) {
		return str != null ? (str.replaceAll("\u00f1", "&#241;").replaceAll("\u00D1", "&#209;")) : "";
	}
	
	/**
	 * Replaces html entity (<, >, &) in a string to its corresponding entity number (&#60;, &#62;, &#38;)
	 * @param str the String to be escaped
	 * @return escaped String
	 */
	public static String escapeHTML(String str) {
		return str != null ? (str.replaceAll("&", "&#38;").replaceAll("<", "&#60;").replaceAll(">", "&#62;").replaceAll("\"", "&#34;").replaceAll("'", "&#039;").replaceAll("\n", "&#13;").replaceAll("\r\n", "&#13;").replaceAll("\r", "&#13;")) : "";
	}
	
	/**
	 * Replaces html entity number (&#38;, &#34;, &#039;, &#62;, &#60;, &#241;, &#209;) in a string to its corresponding html entity character (&, ", ', >, <, ñ, Ñ)
	 * @param str - string to be unescaped
	 * @return unescaped String
	 */
	public static String unescapeHTML(String str) {
		return str != null ? (str.replaceAll("&#38;", "&").replaceAll("&#34;", "\"").replaceAll("&#039;", "'").replaceAll("&#62;", ">").replaceAll("&#60;", "<").replaceAll("&#241;", "ñ").replaceAll("&#209;", "Ñ")) : "";
	}

}
