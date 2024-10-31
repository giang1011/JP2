package Entity;

import java.time.LocalDate;

public class Employee {

        private int id;
        private String name;
        private int deptId;
        private LocalDate DoB;
        private Gender gender;
        private String city;
        private String province;
        private String phoneNumber;

        public Employee() {;}

    public Employee(int id, String name, int deptId, LocalDate doB, Gender gender, String city, String province, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.deptId = deptId;
        DoB = doB;
        this.gender = gender;
        this.city = city;
        this.province = province;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public LocalDate getDoB() {
        return DoB;
    }

    public void setDoB(LocalDate doB) {
        DoB = doB;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", deptId=" + deptId +
                ", DoB=" + DoB +
                ", gender=" + gender +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}




