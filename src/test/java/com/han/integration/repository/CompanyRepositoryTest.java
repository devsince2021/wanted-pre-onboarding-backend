package com.han.integration.repository;

import com.han.model.Company;
import com.han.repository.CompanyRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Log4j2
@DataJpaTest
public class CompanyRepositoryTest {

  @Autowired
  private TestEntityManager testEntityManager;
  @Autowired
  private CompanyRepository companyRepository;

  @Nested
  class FindById_Test {

    private Integer validId = 1;
    private Integer invalidId = 6;

    @Test
    public void findById_Returns_Optional_Empty_When_Id_Is_Invalid() {
      Optional<Company> maybeCompany = companyRepository.findById(invalidId);
      assertThat(maybeCompany.isPresent()).isFalse();
    }
    @Test
    public void findById_Returns_Optional_Company_When_Id_Is_Valid() {
      Optional<Company> maybeCompany = companyRepository.findById(validId);
      assertThat(maybeCompany.isPresent()).isTrue();
    }
  }

  @Nested
  class Save_Test {

    @Test
    public void save_Returns_Updated_Company_When_Company_Existed() {
      Company initialCompany = new Company("wanted4", "US", "NY");
      Company savedCompany = companyRepository.save(initialCompany);

      Company changedCompany = new Company(savedCompany.getId(), "wanted4-1", "US", "NY");
      Company updatedCompany = companyRepository.save(changedCompany);

      assertThat(updatedCompany.getId()).isEqualTo(savedCompany.getId());
      assertThat(updatedCompany.getName()).isEqualTo(changedCompany.getName());
    }

    @Test
    public void save_Throws_InvalidDataAccessApiUsageException_When_Entity_Is_Null() {
      Company invalidCompany = null;
      assertThrows(InvalidDataAccessApiUsageException.class, () -> companyRepository.save(invalidCompany));
    }

    @Test
    public void save_Return_Company() {
      Company company = new Company("wanted4", "US", "NY");
      Company savedCompany = companyRepository.save(company);

      assertThat(savedCompany.getId()).isNotNull();
      assertThat(savedCompany.getName()).isEqualTo(company.getName());
    }
  }

}
