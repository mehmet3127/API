package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DummyRestApiDataPojo {

    private String employee_name;
    private Integer employee_salary;
    private Integer employee_age;
    private String employee_image;

    public DummyRestApiDataPojo(String employee_name, Integer employee_salary, Integer employee_age, String employee_image) {
        this.employee_name = employee_name;
        this.employee_salary = employee_salary;
        this.employee_age = employee_age;
        this.employee_image = employee_image;
    }

    public DummyRestApiDataPojo() {
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public Integer getEmployee_salary() {
        return employee_salary;
    }

    public void setEmployee_salary(Integer employee_salary) {
        this.employee_salary = employee_salary;
    }

    public Integer getEmployee_age() {
        return employee_age;
    }

    public void setEmployee_age(Integer employee_age) {
        this.employee_age = employee_age;
    }

    public String getEmployee_image() {
        return employee_image;
    }

    public void setEmployee_image(String employee_image) {
        this.employee_image = employee_image;
    }

    @Override
    public String toString() {
        return "DummyRestApiDataPojo{" +
                "employee_name='" + employee_name + '\'' +
                ", employee_salary=" + employee_salary +
                ", employee_age=" + employee_age +
                ", employee_image='" + employee_image + '\'' +
                '}';
    }
}
