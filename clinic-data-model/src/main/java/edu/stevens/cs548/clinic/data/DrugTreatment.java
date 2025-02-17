package edu.stevens.cs548.clinic.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Entity implementation class for Entity: DrugTreatment
 *
 */
// TODO JPA annotations
@Entity
public class DrugTreatment extends Treatment implements Serializable {

	private static final long serialVersionUID = 1L;

	private String drug;

	private float dosage;

	private LocalDate startDate;

	private LocalDate endDate;

	private int frequency;

	public String getDrug() {
		return drug;
	}

	public void setDrug(String drug) {
		this.drug = drug;
	}

	public float getDosage() {
		return dosage;
	}

	public void setDosage(float dosage) {
		this.dosage = dosage;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}


	public DrugTreatment() {
		super();
	}

}