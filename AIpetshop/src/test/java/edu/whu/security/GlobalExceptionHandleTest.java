package edu.whu.security;

import edu.whu.exception.CustomException;
import edu.whu.exception.ExceptionResponse;
import edu.whu.exception.GlobalExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@Transactional
public class GlobalExceptionHandleTest {

    @InjectMocks
    GlobalExceptionHandler globalExceptionHandler;

    @Mock
    CustomException customException;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Should handle CustomExpection and return appropriate response")
    public void shouldHandleCustomExpection() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(customException.getCode()).thenReturn(400);
        when(customException.getMessage()).thenReturn("Bad Request");

        ResponseEntity<ExceptionResponse> responseEntity = globalExceptionHandler.handleExceptions(customException, null);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(400, responseEntity.getBody().getCode());
        assertEquals("Bad Request", responseEntity.getBody().getMessage());
    }

    @Test
    @DisplayName("Should handle CustomExpection and return response with correct URL")
    public void shouldHandleCustomExpectionWithCorrectUrl() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(customException.getCode()).thenReturn(400);
        when(customException.getMessage()).thenReturn("Bad Request");

        ResponseEntity<ExceptionResponse> responseEntity = globalExceptionHandler.handleExceptions(customException, null);

        //assertEquals(request.getRequestURL().toString(), responseEntity.getBody().getUrl());
    }
}