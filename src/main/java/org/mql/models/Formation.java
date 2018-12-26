package org.mql.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

// Test Pull
@Entity
@Table(name = "formation")
public class Formation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "form_id")
	private int id;

	@Column(name = "title")
	private String title;

	@Column(name = "creatingDate")
	private String creatingDate;

	@Column(name = "category")
	private String category;

	@OneToMany(mappedBy = "formation", cascade = CascadeType.ALL)
	private List<Module> modules;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.DETACH })
	@JoinTable(name = "following", joinColumns = @JoinColumn(name = "form_id"), inverseJoinColumns = @JoinColumn(name = "memb_id"))
	private List<Member> members;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH })
	@JoinColumn(name = "memb_id")
	private Member creator;

	public Formation() {

	}

	public Formation(String title, String creatingDate, String category) {
		super();
		this.title = title;
		this.creatingDate = creatingDate;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCreatingDate() {
		return creatingDate;
	}

	public void setCreatingDate(String creatingDate) {
		this.creatingDate = creatingDate;
	}


	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	public Member getCreator() {
		return creator;
	}

	public void setCreator(Member creator) {
		this.creator = creator;
	}

	@Override
	public String toString() {
		return "F:Title : "+title+" F:Category: "+category;
	}

	public void add(Module mod) {
		if (modules == null) {

			modules = new ArrayList<Module>();

		}
		modules.add(mod);
		mod.setFormation(this);
	}

	public void addMember(Member membr) {
		if (members == null) {

			members = new ArrayList<Member>();

		}
		members.add(membr);
	}

}
