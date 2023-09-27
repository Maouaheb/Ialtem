package net.codejava.ProductManager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService {
	@Autowired
private ProductRepository repo;
	public List<Product> listeAll(){
		return repo.findAll();		
	}
	public Product getProduct(int id) {
		return repo.findById(id).get();
	}
	public void delete(int id) {
		repo.deleteById(id);
	}
	public void addProduct(Product entity) {
		repo.save(entity);
	}
}
