package com.codehouse.controllers;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.codehouse.dao.ProductDAO;
import br.com.codehouse.model.BookType;
import br.com.codehouse.model.Product;
import br.com.codehouse.utils.FileSaver;


@Controller
@Transactional
@RequestMapping("/produtos")
public class ProductsController {

	
//	@InitBinder
//	protected void initBinder(WebDataBinder binder){
////		binder.setValidator(new ProductValidate());
//	}
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private FileSaver fileSaver;
	
	@RequestMapping(value="/salvar", method=RequestMethod.POST )
	public ModelAndView save(MultipartFile summary,@Valid Product product,BindingResult bindingResult,RedirectAttributes redirectAttribute){
		
		if(bindingResult.hasErrors()){
			System.out.println("passou no form");
			return form(product);
		}
		String path = fileSaver.write("uploaded-images",summary);
		product.setSummaryPath(path);
		productDAO.save(product);
		redirectAttribute.addFlashAttribute("sucesso","Salvo com sucesso");
		return new ModelAndView("redirect:novo");
	}
	
	
	@RequestMapping("/novo")
	public ModelAndView form(Product product){
		ModelAndView modelAndView = new ModelAndView("products/form");
		modelAndView.addObject("bookTypes",BookType.values());
		return modelAndView;
	}
	
	
	@RequestMapping(value="/form")
	public String list(ModelMap model){
		model.addAttribute("listProduct",productDAO.list());
		return "products/list";
	}
	
	
	
}
