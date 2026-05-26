package com.stockwallet.repository;

import com.stockwallet.entity.Asset;
import com.stockwallet.entity.Portfolio;
import com.stockwallet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    Optional<Portfolio> findByUserAndAsset(User user, Asset asset);

}