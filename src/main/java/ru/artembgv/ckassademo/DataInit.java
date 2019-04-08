package ru.artembgv.ckassademo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import ru.artembgv.ckassademo.dao.PersonRepository;
import ru.artembgv.ckassademo.entity.Person;

/**
 * Вставляет начальные данные в БД, если она пустая
 * @author artem
 *
 */
@Component
public class DataInit implements ApplicationRunner {

	private PersonRepository personRepository;
	private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	public DataInit(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		long count = personRepository.count();
		if (count == 0) {
			Person p1 = new Person();
			p1.setFio("Иванов Иван Иванович");
			p1.setBirthDate(df.parse("1991-01-02"));
			personRepository.save(p1);

			Person p2 = new Person();
			p2.setFio("Петров Пётр Петрович");
			p2.setBalance(100.01);
			p2.setInn(123456789012l);
			personRepository.save(p2);
		}
	}
}