package edu.stevens.cs548.clinic.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import static jakarta.persistence.CascadeType.PERSIST;

/**
 * Entity implementation class for Entity: Patient
 *
 */
@NamedQueries({
		@NamedQuery(
				name="SearchProviderByProviderId",
				query="select p from Provider p where p.providerId = :providerId"),
		@NamedQuery(
				name="CountProviderByProviderId",
				query="select count(p) from Provider p where p.providerId = :providerId"),
		@NamedQuery(
				name = "RemoveAllProviders",
				query = "delete from Provider p")
})
// TODO
@Entity
@Table(indexes = @Index(columnList="providerId"))
public class Provider implements Serializable {

	private static final long serialVersionUID = -876909316791083094L;

	// TODO JPA annotations
	@Id
	@GeneratedValue
	private long id;

	// TODO
	@Column(nullable = false, unique = true)
	private UUID providerId;

	private String npi;

	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UUID getProviderId() {
		return providerId;
	}

	public void setProviderId(UUID providerId) {
		this.providerId = providerId;
	}

	public String getNpi() {
		return npi;
	}

	public void setNpi(String npi) {
		this.npi = npi;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// TODO JPA annotations (propagate persist of provider to treatments)
	@OneToMany(cascade = PERSIST, mappedBy = "provider")
	private Collection<Treatment> treatments;


	/*
	 * Addition and deletion of treatments should be done here.
	 */

	public boolean administers(Treatment t) {
		return treatments.contains(t);
	}

	public void addTreatment (Treatment t) {
		/*
		 * TODO complete this operation (see patient entity)
		 */
		treatments.add(t);
		if (t.getProvider() != this) {
			t.setProvider(this);
		}
	}


	public Provider() {
		super();
		treatments = new ArrayList<Treatment>();
	}

}