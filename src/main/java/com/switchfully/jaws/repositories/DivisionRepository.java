package com.switchfully.jaws.repositories;

import com.switchfully.jaws.domain.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DivisionRepository extends JpaRepository<Division, Long> {
    Optional<Division> findDivisionByName(String name);

    @Query(value = "select d.parent_div_fk from division d where d.name = :name" ,
    nativeQuery = true)
    Optional<Long> findDivisionsBySubDivisionsIn(@Param("name") String name);
}
