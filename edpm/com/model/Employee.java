package com.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;

import com.model.Department;
import com.model.Project;

/**
 * This class represents employee details.
 */
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int id;

    @Column(name = "employee_name", nullable = false)
    private String name;

    @Column(name = "place")
    private String place;

    @Column(name = "date_of_birth")
    private String dob;

    @Column(name = "experience")
    private int experience;

    @Column(name = "salary")
    private double salary;

    @Column(name = "is_present")
    private int isPresent;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToMany(mappedBy = "employees", fetch = FetchType.EAGER)
    private Set<Project> projects;

    public Employee(int id, String name, String place, String dob, int experience,
                    double salary, Department department, int isPresent) {
        this.id = id;
        this.name = name;
        this.place = place;
        this.dob = dob;
        this.experience = experience;
        this.salary = salary;
        this.isPresent = isPresent;
        this.department = department;
        this.projects = new HashSet<>();
    }

    public Employee() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPlace() {
        return place;
    }

    public String getDob() {
        return dob;
    }

    public int getExperience() {
        return experience;
    }

    public double getSalary() {
        return salary;
    }

    public int getIsPresent() {
        return isPresent;
    }

    public Department getDepartment() {
        return department;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setIsPresent(int isPresent) {
        this.isPresent = isPresent;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public String displayProject() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Project project : projects) {
            stringBuilder.append(project.getProjectName() + "; ");
        }
        return stringBuilder.toString();
    }

    public String toString() {
        return "Employee" +
                "id = " + id +
                ", name ='" + name + '\'' +
                ", place ='" + place + '\'' +
                ", dob ='" + dob + '\'' +
                ", experience = " + experience +
                ", salary = " + salary + 
                ", Department = " + department;
    }
}