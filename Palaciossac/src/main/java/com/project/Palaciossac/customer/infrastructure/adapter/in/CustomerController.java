package com.project.Palaciossac.customer.infrastructure.adapter.in;

import com.project.Palaciossac.customer.application.port.in.CreateCustomerUseCase;
import com.project.Palaciossac.customer.application.port.in.DeleteCustomerUseCase;
import com.project.Palaciossac.customer.application.port.in.GetCustomerUseCase;
import com.project.Palaciossac.customer.application.port.in.UpdateCustomerUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final GetCustomerUseCase getCustomerUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;
    private final DeleteCustomerUseCase deleteCustomerUseCase;

    public CustomerController(
            CreateCustomerUseCase createCustomerUseCase,
            GetCustomerUseCase getCustomerUseCase,
            UpdateCustomerUseCase updateCustomerUseCase,
            DeleteCustomerUseCase deleteCustomerUseCase) {
        this.createCustomerUseCase = createCustomerUseCase;
        this.getCustomerUseCase = getCustomerUseCase;
        this.updateCustomerUseCase = updateCustomerUseCase;
        this.deleteCustomerUseCase = deleteCustomerUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto create(@Valid @RequestBody CustomerDto request) {
        return CustomerWebMapper.toCustomerDto(createCustomerUseCase.create(CustomerWebMapper.toCommand(request)));
    }

    @GetMapping
    public List<CustomerDto> findAll() {
        return CustomerWebMapper.toListOfCustomerDto(this.getCustomerUseCase.findAll());
    }

    @GetMapping("/{id}")
    public CustomerDto findById(@PathVariable Long id) {
        return CustomerWebMapper.toCustomerDto(getCustomerUseCase.findById(id));
    }

    @GetMapping("/by-document")
    public CustomerDto findByDocument(@RequestParam String document) {
        return CustomerWebMapper.toCustomerDto(getCustomerUseCase.findByDocument(document));
    }

    @PutMapping("/{id}")
    public CustomerDto update(@PathVariable Long id, @Valid @RequestBody CustomerDto request) {
        return CustomerWebMapper.toCustomerDto(
                updateCustomerUseCase.update(id, CustomerWebMapper.toUpdateCommand(request)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        deleteCustomerUseCase.delete(id);
    }
}
