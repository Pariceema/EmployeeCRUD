package com.employee.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.employee.dto.Employeedto;
import com.employee.response.PaginationResponse;
import com.employee.service.Employeeservice;
import com.fasterxml.jackson.databind.ObjectMapper;
@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {
		private MockMvc mockMvc;
		private ObjectMapper objectMapper = new ObjectMapper();
		@Mock
	    private Employeeservice employeeservice;

	    @InjectMocks
	    private EmployeeController employeeController;
	    
	    @BeforeEach
	    void setUp() {
	        mockMvc = MockMvcBuilders
	                .standaloneSetup(employeeController)
	                .build();
	    }
	    
	    @Test
	    void addEmployee_success() throws Exception {

	        Employeedto requestDto = new Employeedto();
	        requestDto.setEmpId("EMP001");
	        requestDto.setName("Rahul");
	        requestDto.setEmail("rahul@gmail.com");
	        requestDto.setDesignation("Java Developer");

	        Employeedto responseDto = new Employeedto();
	        responseDto.setEmpId("EMP001");
	        responseDto.setName("Rahul");
	        responseDto.setEmail("rahul@gmail.com");
	        responseDto.setDesignation("Java Developer");

	        when(employeeservice.createEmployee(any(Employeedto.class), any(Long.class)))
	                .thenReturn(responseDto);

	        mockMvc.perform(
	                post("/api/v1/add/1")
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .content(objectMapper.writeValueAsString(requestDto))
	        )
	        .andExpect(status().isCreated())
	        .andExpect(jsonPath("$.name").value("Rahul"))
	        .andExpect(jsonPath("$.email").value("rahul@gmail.com"));
	    }
	    
	    @Test
	    void getEmployee_success() throws Exception {

	        Employeedto responseDto = new Employeedto();
	        responseDto.setEmpId("EMP001");
	        responseDto.setName("Rahul");
	        responseDto.setEmail("rahul@gmail.com");
	        responseDto.setDesignation("Java Developer");

	        when(employeeservice.getEmployee(1L))
	                .thenReturn(responseDto);

	        mockMvc.perform(
	                get("/api/v1/get/1")
	        )
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.name").value("Rahul"))
	        .andExpect(jsonPath("$.email").value("rahul@gmail.com"))
	        .andExpect(jsonPath("$.designation").value("Java Developer"));
	    }

	    @Test
	    void deleteEmployee_success() throws Exception {

	        doNothing().when(employeeservice).deleteEmployee(1L);

	        mockMvc.perform(
	                delete("/api/v1/delete/1")
	        )
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.message")
	                .value("Employee deleted succesfully"))
	        .andExpect(jsonPath("$.status")
	                .value(true));
	    }
	    
	    @Test
	    void getAllEmployee_success() throws Exception {

	        PaginationResponse response = new PaginationResponse();

	        response.setCurrentpagedata(2);
	        response.setLastpage(false);
	        response.setPagenumber(0);
	        response.setPagesize(10);
	        response.setTotaldata(2L);
	        response.setTotalpage(1);
	        response.setList(List.of());

	        when(employeeservice.getallemployee(
	                "name",
	                "ASC",
	                0,
	                10
	        )).thenReturn(response);

	        mockMvc.perform(
	                get("/api/v1/getall/")
	                        .param("sortBy", "name")
	                        .param("sortDirection", "ASC")
	                        .param("pageNumber", "0")
	                        .param("pageSize", "10")
	        )
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.currentpagedata").value(2))
	        .andExpect(jsonPath("$.pagenumber").value(0))
	        .andExpect(jsonPath("$.pagesize").value(10))
	        .andExpect(jsonPath("$.totaldata").value(2))
	        .andExpect(jsonPath("$.totalpage").value(1));
	    }
	    
	    @Test
	    void addEmployee_validationError() throws Exception {

	        Employeedto requestDto = new Employeedto();

	        // Required fields intentionally empty

	        mockMvc.perform(
	                post("/api/v1/add/1")
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .content(objectMapper.writeValueAsString(requestDto))
	        )
	        .andExpect(status().isBadRequest());
	    }
}
