package br.com.codehouse.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.codehouse.model.Product;

public class ProductValidate implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return  Product.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "title","field.required");
		ValidationUtils.rejectIfEmpty(errors,"description","field.required");
		Product product = (Product)target;
		
		if(product.getPageNumber() == 0){
			 errors.rejectValue("pageNumber","field.required");
		}
		
	}

}
