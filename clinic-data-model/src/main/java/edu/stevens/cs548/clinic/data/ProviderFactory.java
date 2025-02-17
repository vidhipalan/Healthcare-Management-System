package edu.stevens.cs548.clinic.data;

public class ProviderFactory implements IProviderFactory {

	@Override
	public Provider createProvider() {
		return new Provider();
	}

}