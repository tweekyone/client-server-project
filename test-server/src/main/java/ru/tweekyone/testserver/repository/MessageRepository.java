package ru.tweekyone.testserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tweekyone.testserver.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
