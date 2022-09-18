package com.digitalbooks.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
public class PaymentRequest {

	private Set<Integer> bookIds;
}
