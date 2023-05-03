package com.drot.stringutils.service;

import com.drot.stringutils.Employee;
import com.drot.stringutils.exteption.EmployeeNotFoundException;
import com.drot.stringutils.exteption.SyntaxError;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    List<Employee> employee = new ArrayList<>();

    public int searchEmployee(String firstName, String lastName, int salary, int department) {
        syntax(firstName, lastName);

        firstName = StringUtils.capitalize(firstName);
        lastName = StringUtils.capitalize(lastName);

        int index = 0;
        for (; index < employee.size(); ) {
            if (employee.get(index).getFirstName().equals(firstName)) {
                if (employee.get(index).getLastName().equals(lastName)) {
                    break;
                }
            }
            index++;
            if (index == employee.size()) {
                throw new EmployeeNotFoundException();
            }
        }
        return index;
    }

        public String addEmployee (String firstName, String lastName,int salary, int department){
           syntax(firstName, lastName);

           firstName = StringUtils.capitalize(firstName);
           lastName = StringUtils.capitalize(lastName);

            Employee newEmployee = new Employee(firstName, lastName, salary, department);
            if (empSize()) {
                employee.add(newEmployee);
                return employee.get(0) + " Добавлен";
            } else {
                int index;
                try {
                    index = searchEmployee(firstName, lastName, salary, department);
                } catch (EmployeeNotFoundException e) {
                    employee.add(newEmployee);
                    return employee.get(employee.size() - 1) + " Добавлен";
                }
                return employee.get(index) + " EmployeeAlreadyAddedException";
            }
        }

        public String removeEmployee (String firstName, String lastName,int salary, int department){
            int index;
            try {
                index = searchEmployee(firstName, lastName, salary, department);
            } catch (EmployeeNotFoundException e) {
                return "EmployeeNotFoundException";
            }
            return employee.remove(index) + " Удалён";
        }

        public String findEmployee (String firstName, String lastName,int salary, int department){
            int index;
            try {
                index = searchEmployee(firstName, lastName, salary, department);
            } catch (EmployeeNotFoundException e) {
                return "EmployeeNotFoundException";
            }
            return employee.get(index).toString();
        }

        public void syntax(String firstName, String lastName){
            if (!StringUtils.isAlpha(firstName) || !StringUtils.isAlpha(lastName)){
                throw new SyntaxError();
            }
        }

        public boolean empSize () {
            if (employee.size() == 0) {
                return true;
            } else {
                return false;
            }
        }
    }
