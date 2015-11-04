package com.trip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.trip.domain.User;


public interface UserDao extends PagingAndSortingRepository<User, Long>, JpaSpecificationExecutor<User>{
	
	/**根据姓名查找
	 * @param name
	 * @return
	 */
	public List<User> findByName(String name);
	
}
