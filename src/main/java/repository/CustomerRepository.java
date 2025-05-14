package repository;

import Models.Customer;
import Models.DTO.CreateCustomerrDTO;
import Models.DTO.UpdateCustomerDTO;

import java.util.List;

public interface CustomerRepository {

    void create(CreateCustomerrDTO dto);

    Customer findById(int id);

    List<Customer> findAll();

    void update(UpdateCustomerDTO dto);

    void delete(int id);
}
