package controller;

import model.Customer;
import model.Project;
import service.CustomerService;
import service.ProjectService;
import view.ConsoleHelper;
import view.CoreView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomerController extends CoreController<Customer> {
    private DataReceiver dr;
    private ProjectService projectService;
    private CustomerService customerService;

    public CustomerController() {
        super();
        dr = super.getDr();
        projectService = new ProjectService();
        customerService = new CustomerService();
        super.start();
    }

    @Override
    public Customer create() {

        ConsoleHelper.showMessage("Введите name для нового объекта");
        String name = dr.readString();

        Set<Project> projects = new HashSet<>();
        Customer customer = new Customer(name);

        //после создания объекта заполняем команды
        int idProject;

        do {
            ConsoleHelper.showMessage("Введите id project для нового объекта или 0, чтобы продолжить");

            for (Project project : projectService.getAll()){ //показать все проекты, которые есть в базе
                CoreView.show(project);
            }

            idProject = dr.readInt();
            if (idProject == 0) continue;
            if (projectService.getById(idProject) == null){
                ConsoleHelper.showMessage("Project с таким id не существует, перейти в меню сущности project? yes/no");
                if (dr.readBoolean()){
                    ProjectController projectController = new ProjectController(); //переходим в меню skill
                } else {
                    continue;
                }
            }
            projects.add(projectService.getById(idProject));
            ConsoleHelper.showMessage("Добавлен project " + projectService.getById(idProject));

        } while (idProject != 0);
        customer.setProjects(projects);
        return customer;
    }

    @Override
    public void save(Customer customer) {
        if (customerService.save(customer)){
            ConsoleHelper.showMessage("Объект создан");
        } else {
            ConsoleHelper.showMessage("Объект не создан, попробуйте еще раз");
        }
    }

    @Override
    public void read() {
        do{
            ConsoleHelper.showMessage("Введите id объекта или 0 чтобы выйти из этого меню");
            int id = dr.readInt();
            if (id == 0) {
                break;
            }
            Customer customer = customerService.getById(id);
            if (customer != null){
                CoreView.show(customer);
            } else {
                ConsoleHelper.showMessage("Нет такого объекта");
            }
        } while (true);
    }

    @Override
    public void readAll() {
        List<Customer> customers = customerService.getAll();
        if (customers.isEmpty()){
            ConsoleHelper.showMessage("Список пуст");
        } else {
            for (Customer customer: customers){
                CoreView.show(customer);
            }
        }
    }

    @Override
    public void update() {
        Customer customer;
        int id;
        do {
            ConsoleHelper.showMessage("Введите id, который хотите обновить или 0, чтобы выйти");
            id = dr.readInt();
            if (id == 0) return;
            customer = customerService.getById(id);
            if (customer == null){
                ConsoleHelper.showMessage("Нет такого объекта");
            }
        } while (customer == null);

        if (customerService.update(id, create())){
            ConsoleHelper.showMessage("Объект обновлен");
        } else {
            ConsoleHelper.showMessage("Не удалось обновить объект");
        }

    }

    @Override
    public void delete() {
        ConsoleHelper.showMessage("Введите id удаляемого объекта");
        int id = dr.readInt();

        if (customerService.remove(customerService.getById(id))){
            ConsoleHelper.showMessage("Объект удален");
        } else {
            ConsoleHelper.showMessage("Нет такого объекта");
        }
    }

    @Override
    protected void exit() {
        customerService.stopSessionFactory();
    }
}
