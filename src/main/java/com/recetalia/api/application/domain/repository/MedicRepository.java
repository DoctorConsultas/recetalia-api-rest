package com.recetalia.api.application.domain.repository;

import com.recetalia.api.application.domain.model.entities.Medic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Medic entity.
 * Provides CRUD operations for Medic.
 */
@Repository
public interface MedicRepository extends JpaRepository<Medic, String> {
  List<Medic> findByMedicalProviderId(String medicalProviderId);

  @Query(value = "SELECT * FROM medic m WHERE m.medicalProviderId = :medicalProviderId AND " +
          "(m.name LIKE %:searchCriteria% OR m.lastname LIKE %:searchCriteria% OR m.email LIKE %:searchCriteria%) " +
          "LIMIT :limit", nativeQuery = true)
  List<Medic> findByMedicalProviderIdAndSearchCriteria(@Param("medicalProviderId") String medicalProviderId,
                                                       @Param("searchCriteria") String searchCriteria,
                                                       @Param("limit") int limit);
}
