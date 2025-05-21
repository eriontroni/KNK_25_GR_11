package Services;

import Models.Employee;
import Models.Users;
import repository.EmployeeRepository;
import repository.UsersRepository;

import java.util.ArrayList;

public class ChartsService {
    public static int getNrUsers(){
        int nrUsers;
        UsersRepository ur = new UsersRepository();
        ArrayList<Users> userat = new ArrayList<Users>(ur.getAll());
        nrUsers = userat.size();
        return nrUsers;
    }
    public static int getNrRole(String position){
        int nrRep;
        EmployeeRepository er = new EmployeeRepository();
        ArrayList<Employee> puntort = new ArrayList<>(er.getByRole(position));
        nrRep = puntort.size();
        return nrRep;
    }
}
