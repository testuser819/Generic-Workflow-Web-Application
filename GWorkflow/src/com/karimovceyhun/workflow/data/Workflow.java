package com.karimovceyhun.workflow.data;

import java.beans.Transient;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


@Entity
@Table(name="workflow")
public class Workflow implements Bean,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7483734325348712174L;

	Long id;
	String name;
	Integer maxProcessingTime;
	Boolean usingDueDate;

	Set<Project> projects;
	User manager;
	Set<User> userAbleToMonitor;
	Set<User> userAbleToOpen;
	TaskNode startProcess;
	List<Node> nodes;
	Set<Field> fields;
	private Boolean projectDependant = Boolean.TRUE;

	@Id
	@GeneratedValue
	@Override
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany
	public Set<Project> getProjects() {
		return projects;
	}
	
	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}
	
	@ManyToMany
	@JoinTable(name="workflow_usermonitor")
	//@Fetch(FetchMode.JOIN)
	//@LazyCollection(LazyCollectionOption.FALSE)
	//@JoinTable(name="workflow_usermonitor")

	public Set<User> getUserAbleToMonitor() {
		return userAbleToMonitor;
	}
	public void setUserAbleToMonitor(Set<User> userAbleToMonitor) {
		this.userAbleToMonitor = userAbleToMonitor;
	}
	public void setUserAbleToOpen(Set<User> userAbleToOpen) {
		this.userAbleToOpen = userAbleToOpen;
	}
	
	@ManyToMany
	@JoinTable(name="workflow_useropen")
	//@Fetch(FetchMode.JOIN)
	//@LazyCollection(LazyCollectionOption.FALSE)
	//@JoinTable(name="workflow_useropen")

	public Set<User> getUserAbleToOpen() 
	{
		return userAbleToOpen;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	//@PrimaryKeyJoinColumn
	public TaskNode getStartProcess() 
	{
		return startProcess;
	}
	public void setStartProcess(TaskNode startProcess) {
		this.startProcess = startProcess;
	}
	
	@OneToMany(cascade=CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	public List<Node> getNodes() {
		return nodes;
	}
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
	
	@ManyToMany
	public Set<Field> getFields() {
		return fields;
	}
	public void setFields(Set<Field> fields) {
		this.fields = fields;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}
	
	@ManyToOne(cascade=CascadeType.ALL)
	public User getManager() {
		return manager;
	}
	
	public void setProjectDependant(Boolean projectDependant) {
		this.projectDependant = projectDependant;
	}
	
	public Boolean getProjectDependant() {
		return projectDependant;
	}
	
	@Override
	public boolean equals(Object obj) {
		if( obj instanceof Workflow )
		{
			return ((Workflow)obj).getId().equals(getId());
		}
		return super.equals(obj);
	}
	
	
	@Column(nullable=false)
	public Integer getMaxProcessingTime()
	{
		return maxProcessingTime;
	}
	public void setMaxProcessingTime( Integer maxProcessingTime )
	{
		this.maxProcessingTime = maxProcessingTime;
	}
	
	public Boolean getUsingDueDate()
	{
		return usingDueDate;
	}
	public void setUsingDueDate( Boolean usingDueDate )
	{
		this.usingDueDate = usingDueDate;
	}
}
