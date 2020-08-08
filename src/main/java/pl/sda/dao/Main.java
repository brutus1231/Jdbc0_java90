package pl.sda.dao;

public class Main {
    public static void main(String[] args) {
        DepartmentDaoImpl departmentDao = new DepartmentDaoImpl();
        departmentDao.printDetails(1);

        departmentDao.printAll();
    }
}
