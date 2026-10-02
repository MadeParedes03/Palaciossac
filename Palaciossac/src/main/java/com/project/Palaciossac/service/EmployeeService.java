package com.project.Palaciossac.service;

import com.project.Palaciossac.dto.EmployeeRequest;
import com.project.Palaciossac.dto.EmployeeResponse;
import com.project.Palaciossac.entity.Employee;
import com.project.Palaciossac.exception.ResourceNotFoundException;
import com.project.Palaciossac.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public EmployeeResponse create(EmployeeRequest request) {
        Employee employee = new Employee(request.getName(), request.getPosition(), request.getPhone());
        return toResponse(employeeRepository.save(employee));
    }

    public List<EmployeeResponse> findAll() {
        return employeeRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public EmployeeResponse findById(Long id) {
        return toResponse(getEntity(id));
    }

    public List<EmployeeResponse> findByPosition(String position) {
        return employeeRepository.findByPositionIgnoreCase(position).stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public EmployeeResponse update(Long id, EmployeeRequest request) {
        Employee employee = getEntity(id);
        employee.setName(request.getName());
        employee.setPosition(request.getPosition());
        employee.setPhone(request.getPhone());
        return toResponse(employeeRepository.save(employee));
    }

    @Transactional
    public void delete(Long id) {
        employeeRepository.delete(getEntity(id));
    }

    private Employee getEntity(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado: " + id));
    }

    private EmployeeResponse toResponse(Employee e) {
        return new EmployeeResponse(e.getId(), e.getName(), e.getPosition(), e.getPhone());
    }
}
