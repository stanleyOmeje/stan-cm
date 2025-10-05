package com.stan.stancommons.repository;

import com.systemspecs.remita.vending.vendingcommon.entity.Category;
import com.systemspecs.remita.vending.vendingcommon.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByCode(String code);

    Page<Product> findByCategory(Category category, Pageable pageable);


    @Query(value = "SELECT " +
            "  (SELECT COUNT(*) FROM product p WHERE p.code = :code) AS productExists, " +
            "  c.* " +
            "FROM category c " +
            "WHERE c.code = :categoryCode",
            nativeQuery = true)
    Optional<Tuple> findProductExistsAndCategory(@Param("code") String code, @Param("categoryCode") String categoryCode);

}

