CREATE TABLE IF NOT EXISTS companies (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS posts (
    id INT PRIMARY KEY AUTO_INCREMENT,
    company_id INT NOT NULL,
    job_position VARCHAR(255) NOT NULL,
    compensation INT,
    job_description VARCHAR(65535) NOT NULL,
    tech_stack VARCHAR(255) NOT NULL,

    FOREIGN KEY (company_id) REFERENCES companies(id)
);
