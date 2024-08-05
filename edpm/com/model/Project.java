package com.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.model.Employee;


/**
 * This class contains project details.
 */
@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private int projectId;

    @Column(name = "project_name", nullable = false)
    private String projectName;

    @Column(name = "is_present")
    private int isPresent;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "employee_project",
        joinColumns = { @JoinColumn(name = "project_id") },
        inverseJoinColumns = { @JoinColumn(name = "employee_id") }
    )
    private Set<Employee> employees;

    public Project(String projectName) {
        this.projectName = projectName;
        this.isPresent = 1;
        this.employees = new HashSet<Employee>();
    }
   
    public Project() {
    }

    public int getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public int getIsPresent() {
        return isPresent;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setIsPresent(int isPresent) {
        this.isPresent = isPresent;
    }

    public void setProjectId(int ProjectId) {
        this.projectId = ProjectId;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public String toString() {
        return "Project" +
               "ProjectId = " + projectId +
               ", ProjectName = " + projectName + '\'';
    }
}