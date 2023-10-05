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
  class Save_Test {

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
