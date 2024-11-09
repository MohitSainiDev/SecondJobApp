package com.mohit.SecondJobApp.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@GetMapping
	public ResponseEntity<List<Company>> findAll() {
		return ResponseEntity.ok(companyService.findAll());

	}

	@PostMapping
	public ResponseEntity<String> createCompany(@RequestBody Company company) {
		companyService.createCompany(company);
		return new ResponseEntity<>("Company created Successfully", HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {

		Company company = companyService.getCompanyById(id);
		if (company != null)
			return new ResponseEntity<>(company, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCompanyById(@PathVariable Long id) {

		boolean value = companyService.deleteCompanyById(id);
		if (value)
			return new ResponseEntity<>("Company deleted Successfully", HttpStatus.OK);
		return new ResponseEntity<>("Company Not found", HttpStatus.NOT_FOUND);
	}

	// @RequestMapping(value ="/jobs/{id}" ,method = RequestMethod.PUT )
	@PutMapping("/{id}")
	public ResponseEntity<String> updateCompanyById(@PathVariable Long id, @RequestBody Company company) {

		boolean value = companyService.updateCompanyById(id, company);
		if (value)
			return new ResponseEntity<>("Company updated Successfully", HttpStatus.OK);
		return new ResponseEntity<>("Company Not found", HttpStatus.NOT_FOUND);
	}
}
