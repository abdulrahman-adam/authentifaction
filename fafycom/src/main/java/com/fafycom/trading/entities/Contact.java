package com.fafycom.trading.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
// builder annotation will help me build my object User
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Contact {
	@Id
	@GeneratedValue
		private Long id;
		private String fullname;
		private String telephone;
		private String email;
		private String message;

}
