package com.mohit.SecondJobApp.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohit.SecondJobApp.company.Company;
import com.mohit.SecondJobApp.company.CompanyRepository;
import com.mohit.SecondJobApp.company.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public List<Company> findAll() {
		return companyRepository.findAll();
	}

	@Override
	public void createCompany(Company company) {
		companyRepository.save(company);

	}

	@Override
	public Company getCompanyById(Long id) {
		return companyRepository.findById(id).orElse(null);
	}

	@Override
	public boolean deleteCompanyById(Long id) {
		if (getCompanyById(id) == null)
			return false;
		else {
			companyRepository.deleteById(id);
			return true;
		}
	}

	@Override
	public boolean updateCompanyById(Long id, Company Updatedcompany) {
		if (getCompanyById(id) != null) {
			Company company = getCompanyById(id);
			company.setDescription(Updatedcompany.getDescription());
			company.setName(Updatedcompany.getName());
			company.setJobs(Updatedcompany.getJobs());
			companyRepository.save(company);
			return true;
		}

		return false;
	}

}
