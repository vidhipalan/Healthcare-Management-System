package edu.stevens.cs548.clinic.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDate;

//TODO JPA annotations
@Entity
public class SurgeryTreatment extends Treatment {

	/**
	 *
	 */
	private static final long serialVersionUID = 4173146640306267418L;

	private LocalDate surgeryDate;

	private String dischargeInstructions;

	public LocalDate getSurgeryDate() {
		return surgeryDate;
	}

	public void setSurgeryDate(LocalDate surgeryDate) {
		this.surgeryDate = surgeryDate;
	}

	public String getDischargeInstructions() {
		return dischargeInstructions;
	}

	public void setDischargeInstructions(String dischargeInstructions) {
		this.dischargeInstructions = dischargeInstructions;
	}

	public SurgeryTreatment() {
		super();
	}

}