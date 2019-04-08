package ru.artembgv.ckassademo.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ru.artembgv.ckassademo.entity.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer>{
	public Optional<Person> findByInn(long inn);
}
