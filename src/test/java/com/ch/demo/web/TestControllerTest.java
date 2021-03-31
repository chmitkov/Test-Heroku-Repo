package com.ch.demo.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class TestControllerTest {

    private static final String TEST_CONTROLLER_PREFIX = "/test";

    @Autowired
    private MockMvc mockMvc;

//    @BeforeEach
//    void setUp() {
//    }

    @Test
    void multipartFormGet() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get(TEST_CONTROLLER_PREFIX + "/multipart"))
                .andExpect(status().isOk())
                .andExpect(view().name("multipart-demo"));
    }

    @Test
    @WithMockUser(username = "gosho", roles = {"USER", "ADMIN"})
    void multipartFormConfirmWithValidParametersShouldReturnStatusOk() throws Exception {
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file",
                "file.txt", MediaType.TEXT_PLAIN_VALUE,
                "This is the file content".getBytes());

        mockMvc.perform(MockMvcRequestBuilders
                .multipart(TEST_CONTROLLER_PREFIX + "/multipart")
                .file(mockMultipartFile)
                .param("name", "TestName")
                .with(csrf()))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(username = "gosho", roles = {"USER", "ADMIN"})
    void multipartFormConfirmWithInvalidParametersShouldThrowException() throws Exception {
        MockMultipartFile emptyMockMultipartFile = new MockMultipartFile("file",
                "file.txt", MediaType.TEXT_PLAIN_VALUE,
                new byte[0]);
       //Without multipart file
        //When validate date can throw another exception

        mockMvc.perform(MockMvcRequestBuilders
                .multipart(TEST_CONTROLLER_PREFIX + "/multipart")
                .file(emptyMockMultipartFile)
                .param("name", "TestName")
                .with(csrf()))
                .andExpect(mvcResult -> {
                    assertTrue(mvcResult.getResolvedException() instanceof IllegalArgumentException);
                });
    }

    @Test
    @WithAnonymousUser
    void multipartFormConfirmWithAnonymousUser() throws Exception {
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file",
                "file.txt", MediaType.TEXT_PLAIN_VALUE,
                "This is the file content".getBytes());

        mockMvc.perform(MockMvcRequestBuilders
                .multipart(TEST_CONTROLLER_PREFIX + "/multipart")
                .file(mockMultipartFile)
                .param("name", "TestName")
                .with(csrf()))
                .andExpect(mvcResult -> {
                    assertTrue(mvcResult.getResolvedException() instanceof AccessDeniedException);
                });
    }

}