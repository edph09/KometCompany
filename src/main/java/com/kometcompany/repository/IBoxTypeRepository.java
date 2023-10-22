package com.kometcompany.repository;

import com.kometcompany.model.BoxType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBoxTypeRepository extends JpaRepository<BoxType,Long> {
}
