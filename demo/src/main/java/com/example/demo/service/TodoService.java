package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.TodoEntity;
import com.example.demo.persistence.TodoRepository;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class TodoService {
	@Autowired
	private TodoRepository repository;
	
	public String testService() {
		//TodoEntity 생성
		TodoEntity entity = TodoEntity.builder().title("My first todo item").build();
		//TodoEntity 저장
		repository.save(entity);
		//TodoEntity 검색
		TodoEntity saveEntity = repository.findById(entity.getId()).get();
		
		return saveEntity.getTitle();
	}
	
	public List<TodoEntity> create(final TodoEntity entity) {
	    // Validations
	    validate(entity);

	    repository.save(entity);

	    log.info("Entity Id : {} is saved", entity.getId());

	    return repository.findByUserId(entity.getUserId());
	}

	private void validate(final TodoEntity entity) {
	    if (entity == null) {  // '='를 '=='로 수정
	        log.warn("Entity cannot be null");
	        throw new RuntimeException("Entity cannot be null");
	    }

	    if (entity.getUserId() == null) {  // '='를 '=='로 수정
	        log.warn("Unknown user.");
	        throw new RuntimeException("Unknown user.");  // 'trow'를 'throw'로 수정
	    }
	}

}
