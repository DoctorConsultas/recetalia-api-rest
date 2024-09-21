package com.recetalia.api.application.domain.repository;

import com.recetalia.api.application.domain.model.entities.Medic;
import com.recetalia.api.application.domain.model.entities.Prescription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Medic entity.
 * Provides CRUD operations for Medic.
 */
@Repository
public interface MedicRepository extends JpaRepository<Medic, String> {

  /**
   * Finds a list of Medics associated with a specific Medical Provider.
   *
   * @param medicalProviderId the ID of the medical provider
   * @return a list of Medics associated with the given medical provider
   */
  List<Medic> findByMedicalProviderId(String medicalProviderId);

  /**
   * Finds a list of Medics associated with a specific Medical Provider,
   * filtered by search criteria such as name, lastname, or email.
   *
   * @param medicalProviderId the ID of the medical provider
   * @param searchCriteria search keyword to match name, lastname, or email
   * @param limit the maximum number of results to return
   * @return a list of Medics matching the search criteria
   */
  @Query(value = "SELECT * FROM medic m WHERE m.medicalProviderId = :medicalProviderId AND " +
          "(m.name LIKE %:searchCriteria% OR m.lastname LIKE %:searchCriteria% OR m.email LIKE %:searchCriteria%) " +
          "LIMIT :limit", nativeQuery = true)
  List<Medic> findByMedicalProviderIdAndSearchCriteria(@Param("medicalProviderId") String medicalProviderId,
                                                       @Param("searchCriteria") String searchCriteria,
                                                       @Param("limit") int limit);

  /**
   * Finds all Medics with pagination support and an optional search keyword.
   * The search keyword can match the name, lastname, email, or cjp of the Medic.
   * If the search keyword is null or empty, all records are returned.
   *
   * @param pageable pagination information including page number and size
   * @param searchKeyword the optional search keyword to match against name, lastname, email, or cjp
   * @return a paginated list of Medic entities
   */
  @Query("SELECT m FROM Medic m " +
          "WHERE (:searchKeyword IS NULL OR :searchKeyword = '' OR " +
          "m.name LIKE %:searchKeyword% OR " +
          "m.lastname LIKE %:searchKeyword% OR " +
          "m.email LIKE %:searchKeyword% OR " +
          "m.cjp LIKE %:searchKeyword%)")
  Page<Medic> findAllWithPagination(@Param("searchKeyword") String searchKeyword, Pageable pageable);

  Optional<Medic> findByEmail(String email);

}
