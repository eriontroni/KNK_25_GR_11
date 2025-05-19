package Services;

import Models.Employee;

public class EmployeeSessionManager {
    private static EmployeeSessionManager instance;
    //    Anetaret e Sessionit
    private Employee employee;

    public static EmployeeSessionManager getInstance(){
        if(instance == null){
            instance = new EmployeeSessionManager();
        }
        return instance;
    }

    public void setCurrentUser(Employee employee){
        this.employee = employee;
    }

    public Employee getCurrentEmployee(){
        return this.employee;
    }
}