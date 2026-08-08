package com.employee.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.employee.dto.Employeedto;
import com.employee.entity.Department;
import com.employee.entity.Employee;
import com.employee.exception.EmptyException;
import com.employee.exception.ResourceNotFoundException;
import com.employee.repo.Departmentrepo;
import com.employee.repo.Employeerepo;
import com.employee.response.PaginationResponse;
import com.employee.service.impl.EmployeeServiceImpl;
@ExtendWith(MockitoExtension.class)

class EmployeeserviceImplTest {

	@Mock
    private Employeerepo employeerepository;

    @InjectMocks
    private EmployeeServiceImpl employeeservice;
    
    @Mock
    private Departmentrepo departmentrepo;

    @Mock
    private ModelMapper modelmapper;
    
    @Test
    void createEmployee_success() {

        // Arrange
        Employeedto dto = new Employeedto();
        dto.setName("Rahul");
        dto.setEmail("rahul@gmail.com");
        dto.setDesignation("Java Developer");

        Department department = new Department();
        department.setId(1L);

        Employee employee = new Employee();
        employee.setName("Rahul");
        employee.setEmail("rahul@gmail.com");
        employee.setDesignation("Java Developer");

        when(departmentrepo.findById(1L))
                .thenReturn(java.util.Optional.of(department));

        when(modelmapper.map(dto, Employee.class))
                .thenReturn(employee);

        when(employeerepository.save(employee))
                .thenReturn(employee);

        when(modelmapper.map(employee, Employeedto.class))
                .thenReturn(dto);

        // Act
        Employeedto result =
                employeeservice.createEmployee(dto, 1L);

        // Assert
        assertNotNull(result);
        assertEquals("Rahul", result.getName());
        assertEquals("rahul@gmail.com", result.getEmail());
    }
    
    @Test
    void createEmployee_departmentNotFound() {

        Employeedto dto = new Employeedto();
        dto.setName("Rahul");
        dto.setEmail("rahul2@gmail.com");
        dto.setDesignation("Java Developer");

        when(departmentrepo.findById(99L))
                .thenReturn(java.util.Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> employeeservice.createEmployee(dto, 99L)
        );
    }
    
    @Test
    void createEmployee_duplicateEmployeeId() {

        Employeedto dto = new Employeedto();
        dto.setEmpId("EMP001");
        dto.setName("Rahul");
        dto.setEmail("rahul3@gmail.com");
        dto.setDesignation("Java Developer");

        Department department = new Department();

        when(departmentrepo.findById(1L))
                .thenReturn(Optional.of(department));

        Employee employee = new Employee();

        when(modelmapper.map(dto, Employee.class))
                .thenReturn(employee);

        when(employeerepository.save(employee))
                .thenThrow(new DataIntegrityViolationException("Duplicate employee id"));

        assertThrows(
                EmptyException.class,
                () -> employeeservice.createEmployee(dto, 1L)
        );
    }
    
    @Test
    void updateEmployee_employeeNotFound() {

        Employeedto dto = new Employeedto();
        dto.setName("Rahul");
        dto.setEmail("rahul4@gmail.com");
        dto.setDesignation("Java Developer");

        when(employeerepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> employeeservice.updateEmployee(dto, 99L, 1L)
        );
    }

    @Test
    void updateEmployee_departmentNotFound() {

        Employeedto dto = new Employeedto();
        dto.setName("Rahul");
        dto.setEmail("rahul5@gmail.com");
        dto.setDesignation("Java Developer");

        Employee employee = new Employee();

        when(employeerepository.findById(1L))
                .thenReturn(Optional.of(employee));

        when(departmentrepo.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> employeeservice.updateEmployee(dto, 1L, 99L)
        );
    }
    
    @Test
    void deleteEmployee_success() {

        Employee employee = new Employee();

        when(employeerepository.findById(1L))
                .thenReturn(Optional.of(employee));

        employeeservice.deleteEmployee(1L);

        verify(employeerepository).delete(employee);
    }
    
    @Test
    void deleteEmployee_employeeNotFound() {

        when(employeerepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> employeeservice.deleteEmployee(99L)
        );

        verify(employeerepository, never()).delete(any(Employee.class));
    }
    
    @Test
    void getEmployee_success() {

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("Rahul");
        employee.setEmail("rahul@gmail.com");
        employee.setDesignation("Java Developer");

        Employeedto dto = new Employeedto();
        dto.setName("Rahul");
        dto.setEmail("rahul@gmail.com");
        dto.setDesignation("Java Developer");

        when(employeerepository.findById(1L))
                .thenReturn(Optional.of(employee));

        when(modelmapper.map(employee, Employeedto.class))
                .thenReturn(dto);

        Employeedto result = employeeservice.getEmployee(1L);

        assertNotNull(result);
        assertEquals("Rahul", result.getName());
        assertEquals("rahul@gmail.com", result.getEmail());
        assertEquals("Java Developer", result.getDesignation());

        verify(employeerepository).findById(1L);
    }
    
    @Test
    void getEmployee_employeeNotFound() {

        when(employeerepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> employeeservice.getEmployee(99L)
        );

        verify(employeerepository).findById(99L);
        verify(modelmapper, never()).map(any(Employee.class), eq(Employeedto.class));
    }
    
    @Test
    void getAllEmployee_success() {

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("Rahul");
        employee.setEmail("rahul@gmail.com");
        employee.setDesignation("Java Developer");

        Employeedto dto = new Employeedto();
        dto.setName("Rahul");
        dto.setEmail("rahul@gmail.com");
        dto.setDesignation("Java Developer");

        Page<Employee> page =
                new PageImpl<>(List.of(employee));

        when(employeerepository.findAll(any(Pageable.class)))
                .thenReturn(page);

        when(modelmapper.map(employee, Employeedto.class))
                .thenReturn(dto);

        PaginationResponse result =
                employeeservice.getallemployee(
                        "name",
                        "ASC",
                        0,
                        10
                );

        assertNotNull(result);
        assertEquals(1, result.getCurrentpagedata());
        assertEquals(1, result.getTotaldata());
        assertEquals(1, result.getTotalpage());
        assertEquals(0, result.getPagenumber());

        verify(employeerepository)
                .findAll(any(Pageable.class));
    }
    
    @Test
    void getAllEmployee_emptyList() {

        Page<Employee> page =
                new PageImpl<>(List.of());

        when(employeerepository.findAll(any(Pageable.class)))
                .thenReturn(page);

        assertThrows(
                EmptyException.class,
                () -> employeeservice.getallemployee(
                        "name",
                        "ASC",
                        0,
                        10
                )
        );
    }
    
    @Test
    void saveEmployee_success() {

        Employee employee = new Employee();
        employee.setName("Rahul");
        employee.setEmail("rahul@gmail.com");
        employee.setDesignation("Java Developer");

        when(employeerepository.save(employee))
                .thenReturn(employee);

        Employee result = employeeservice.saveEmployee(employee);

        assertNotNull(result);
        assertEquals("Rahul", result.getName());
        assertEquals("rahul@gmail.com", result.getEmail());

        verify(employeerepository).save(employee);
    }
}
