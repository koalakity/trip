package com.trip.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.trip.domain.Product;


public interface ProductDao extends PagingAndSortingRepository<Product, Long>, JpaSpecificationExecutor<Product>{
	
	/**根据姓名查找
	 * @param name
	 * @return
	 */
	public List<Product> findByName(String name);
	
	/**根据姓名分页查找
	 * @param name
	 * @param pageable
	 * @return
	 */
	public Page<Product> findByName(String name ,Pageable pageable);
	
}
