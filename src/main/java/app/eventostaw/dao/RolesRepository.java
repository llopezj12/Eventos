package app.eventostaw.dao;

import app.eventostaw.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles, Integer> {
}