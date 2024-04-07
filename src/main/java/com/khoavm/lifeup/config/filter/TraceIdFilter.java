package com.khoavm.lifeup.config.filter;

import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@AllArgsConstructor
@Component
public class TraceIdFilter extends OncePerRequestFilter {



    private Tracer tracer;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var traceContext =  tracer.currentTraceContext().context();

        if (traceContext != null) {
            var traceId = traceContext.traceId();
            response.setHeader("X-Trace-Id", traceId);
        }
       filterChain.doFilter(request, response);
    }
}
