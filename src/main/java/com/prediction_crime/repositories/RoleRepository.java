package com.prediction_crime.repositories;

import com.prediction_crime.dto.RoleDto;
import com.prediction_crime.models.Role;
import com.prediction_crime.models.enums.RoleList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {
    Optional<Role> findByLibelle(RoleList libelle);
}
