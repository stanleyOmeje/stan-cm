package com.stan.stancommons.repository;

import com.systemspecs.remita.vending.vendingcommon.entity.Category;
import com.systemspecs.remita.vending.vendingcommon.entity.Provider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {
    Optional<Provider> findByCode(String code);
    Page<Provider> findByCategory(Category category, Pageable pageable);

    @Query("SELECT p FROM Provider p JOIN p.category c WHERE c.id = :id")
    List<Provider> findProviderByCategory(@Param("id")Long id);
}
