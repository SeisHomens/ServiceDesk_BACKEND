package br.senai.sp.info.service.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class MapUtils {

	public static Map<String, String> mapaDe(BindingResult bindingResult) {
		Map<String, String> map = new HashMap<>();
		
		for(FieldError error : bindingResult.getFieldErrors()) {
			map.put(error.getField(), error.getDefaultMessage());
		}
		return map;
	}
}
