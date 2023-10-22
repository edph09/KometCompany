package com.kometcompany.repository;


import com.kometcompany.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IInventoryRepository extends JpaRepository<Inventory,Long> {

    List<Inventory> findByCompanyId(int companyId);
}

