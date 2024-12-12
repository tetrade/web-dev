package ru.nicetu.predator.u.modules.launch.chart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.nicetu.predator.u.modules.launch.chart.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByLogin(String email);
}
