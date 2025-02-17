package edu.stevens.cs548.clinic.data;

public class PatientFactory implements IPatientFactory {

	@Override
	public Patient createPatient() {
		Patient p = new Patient();
		return p;
	}

}