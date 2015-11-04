package com.trip.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.trip.domain.Product;


public interface ProductDao extends PagingAndSortingRepository<Product, Long>, JpaSpecificationExecutor<Product>{
	
	/**������������
	 * @param name
	 * @return
	 */
	public List<Product> findByName(String name);
	
	/**����������ҳ����
	 * @param name
	 * @param pageable
	 * @return
	 */
	public Page<Product> findByName(String name ,Pageable pageable);
	
}
