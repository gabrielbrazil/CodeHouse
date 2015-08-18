package br.com.codehouse.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.codehouse.model.Product;

@Repository
public class ProductDAO {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void save(Product product){
		manager.persist(product);
	}
	
	
	public List<Product>list(){
		StringBuilder sb = new StringBuilder();
		sb.append("Select distinct product");
		sb.append(" from Product product");
		sb.append(" inner join fetch product.prices price");
		return manager.createQuery(sb.toString(),Product.class).getResultList();
	}
	

}
