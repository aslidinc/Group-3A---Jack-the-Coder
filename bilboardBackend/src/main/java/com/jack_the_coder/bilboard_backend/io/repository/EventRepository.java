package com.jack_the_coder.bilboard_backend.io.repository;

import com.jack_the_coder.bilboard_backend.io.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Hacı Çakın
 * @since  11.11.2021
 * @apiNote This class provides communication between databases and related class(basically for the queries)
 */
public interface EventRepository extends JpaRepository<EventEntity, Long> {
}
