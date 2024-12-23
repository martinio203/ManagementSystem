package com.example.backend;

import com.example.backend.repository.EmployeeRepository;
import com.example.backend.request.AddEmployeeRequest;
import com.example.backend.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


class EmployeeServiceTests {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addEmployee_whenEmployeeDoesNotExist_shouldAddEmployee() {
        AddEmployeeRequest request = new AddEmployeeRequest(2, "Test", "Name", "Testname@email.com",
                "9230521", "2024-12-15", "AD_VP", 2000, 100, 90);

        when(employeeRepository.findEmployeeById(request.getEmployeeId())).thenThrow(new EmptyResultDataAccessException(1));
        when(employeeRepository.addEmployee(request)).thenReturn(1);

        int result = employeeService.addEmployee(request);

        assertEquals(1, result);
        verify(employeeRepository).addEmployee(request);
    }

    @Test
    void addEmployee_whenEmployeeExists_shouldThrowException() {
        AddEmployeeRequest request = new AddEmployeeRequest(2, "Test", "Name", "Testname@email.com",
                "9230521", "2024-12-15", "AD_VP", 2000, 100, 90);
        when(employeeRepository.findEmployeeById(request.getEmployeeId())).thenReturn(Collections.singletonMap("id", 1));

        assertThrows(IllegalArgumentException.class, () -> employeeService.addEmployee(request));
        verify(employeeRepository, never()).addEmployee(request);
    }

    @Test
    void getEmployeeById_whenEmployeeExists_shouldReturnEmployee() {
        int employeeId = 1;
        Map<String, Object> expectedEmployee = Collections.singletonMap("id", employeeId);
        when(employeeRepository.findEmployeeById(employeeId)).thenReturn(expectedEmployee);

        Map<String, Object> result = employeeService.getEmployeeById(employeeId);

        assertEquals(expectedEmployee, result);
    }

    @Test
    void getEmployeeById_whenEmployeeDoesNotExist_shouldThrowException() {
        int employeeId = 1;
        when(employeeRepository.findEmployeeById(employeeId)).thenThrow(new EmptyResultDataAccessException(1));

        assertThrows(EmptyResultDataAccessException.class, () -> employeeService.getEmployeeById(employeeId));
    }

    @Test
    void findAllEmployees_shouldReturnAllEmployees() {
        when(employeeRepository.findAllEmployees()).thenReturn(Collections.emptyList());

        assertEquals(Collections.emptyList(), employeeService.findAllEmployees());
    }
}