package Repository;

import Models.Customer;
import Models.DTO.CreateCustomerrDTO;
import Models.DTO.UpdateCustomerDTO;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {

    private List<Customer> customers = new ArrayList<>();
    private int nextId = 1;

    public void create(CreateCustomerrDTO dto) {
        Customer customer = new Customer(
                nextId++,
                dto.getFirstName(),
                dto.getLastName(),
                dto.getEmail(),
                dto.getPhone()
        );
        customers.add(customer);
    }

    public Customer findById(int id) {
        return customers.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Customer> findAll() {
        return new ArrayList<>(customers);
    }

    public void update(UpdateCustomerDTO dto) {
        for (Customer customer : customers) {
            if (customer.getId() == dto.getId()) {
                customer.setFirstName(dto.getFirstName());
                customer.setLastName(dto.getLastName());
                customer.setEmail(dto.getEmail());
                customer.setPhone(dto.getPhone());
                break;
            }
        }
    }

    public void delete(int id) {
        customers.removeIf(c -> c.getId() == id);
    }
}
