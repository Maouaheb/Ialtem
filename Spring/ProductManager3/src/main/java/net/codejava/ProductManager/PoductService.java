package net.codejava.ProductManager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PoductService {
	@Autowired
private ProductRepository repo;
	public void addProduct(Product product) {
		repo.save(product);
	}
	public List<Product> getAll(){
		return repo.findAll();
	}
	public void delete(int id) {
		repo.deleteById(id);
	}
public Product find(int id) {
	return repo.findById(id).get();
}
}
