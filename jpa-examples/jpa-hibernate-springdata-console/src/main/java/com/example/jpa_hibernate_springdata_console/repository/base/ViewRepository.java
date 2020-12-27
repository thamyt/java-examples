package com.example.jpa_hibernate_springdata_console.repository.base;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

@NoRepositoryBean
public interface ViewRepository<T, ID> extends Repository<T, ID>, QueryByExampleExecutor<T> {
	long count();
	
	boolean existsById(ID id);
	
	List<T> findAll();
	
	List<T> findAll(Sort sort);
	
	Page<T> findAll(Pageable pageable);
	
	List<T> findAllById(Iterable<ID> ids);
	
	Optional<T> findById(ID id);
	
	T getOne(ID id);
	
	@Override
	<S extends T> List<S> findAll(Example<S> example);
	
	@Override
	<S extends T> List<S> findAll(Example<S> example, Sort sort);
}
