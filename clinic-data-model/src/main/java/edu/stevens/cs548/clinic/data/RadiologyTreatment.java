package edu.stevens.cs548.clinic.data;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//TODO JPA annotations
@Entity
public class RadiologyTreatment extends Treatment {

	private static final long serialVersionUID = -3656673416179492428L;

	// TODO including order by date
	@ElementCollection
	@OrderBy
	protected List<LocalDate> treatmentDates;

	public void addTreatmentDate(LocalDate date) {
		treatmentDates.add(date);
	}

	public RadiologyTreatment() {
		super();
		treatmentDates = new ArrayList<>();
	}

}