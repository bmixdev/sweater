package ru.mbelin.sweater.repos;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import ru.mbelin.sweater.domain.Message;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Long> {
    List<Message> findByTag(String tag);
    List<Message> findByTagStartingWith(String tag);
    Iterable<Message> findAll();
}
