package com.khoavm.lifeup.config.context;

import com.khoavm.lifeup.module.common.dto.Query;
import com.khoavm.lifeup.config.security.AuthenticationDetail;
import io.micrometer.tracing.Tracer;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@Component
public class ContextImpl implements Context {


    HttpServletRequest request;
    private Tracer tracer;


    public  UUID getUserId() {
        var authDetail = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (authDetail instanceof AuthenticationDetail){
            return ((AuthenticationDetail) authDetail).userId();
        }
        return new UUID(0L, 0L);
    }

    @Override
    public String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    public String getTraceId() {
        var traceContext =  tracer.currentTraceContext().context() ;
        if (traceContext == null){
            return null;
        }
        return traceContext.traceId();
    }

    @Override
    public Integer getOffset() {
        var limit = this.getLimit();
        var page = this.getPage();
        if (limit != null && page != null){
            return page * limit + limit;
        }
        return null;
    }

    @Override
    public Integer getPage() {
        var page =request.getParameter("page");
        if (page != null) {
            try {
                return Integer.parseInt(page);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    @Override
    public Integer getLimit() {
        var limit = request.getParameter("limit");
        if (limit != null){
            try {
                return Integer.parseInt(limit);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    @Override
    public Pageable getPageable() {
        var page = this.getPage();
        var limit = this.getLimit();
        var sort = this.getSort();
        if(page == null && limit == null && sort == null){
            return null;
        }
        if(page != null && limit != null && sort != null){
            return PageRequest.of(page, limit, sort);
        }
        if (page != null && limit != null) {
            return PageRequest.of(page, limit);
        }
        return null;
    }

    @Override
    public List<Query> getQuery() {
        List<Query> result = new ArrayList<>();
        var queryString = request.getParameter("query");
        if (queryString == null || queryString.isEmpty()){
            return null;
        }
        var queries = queryString.split(",");
        for (var queryInfo : queries){
            var queryArr = queryInfo.split("=");
            if (queryArr.length < 2){
                continue;
            }
            var query = new Query(queryArr[0], queryArr[1]);
            result.add(query);
        }
        return result;

    }

    @Override
    public Sort getSort() {
        var sort = request.getParameter("sort");
        if (sort == null){
            return null;
        }
        var sortFields = sort.split(",");
        var sortList = new ArrayList<Sort.Order>();
        for (var sortField : sortFields) {
            var sortInfo = sortField.split(":");
            if  (sortInfo.length < 1) {
                continue;
            }else if (sortInfo.length == 1){
                sortList.add(Sort.Order.desc(sortInfo[0]));
            }else{
                if (sortInfo[1].equalsIgnoreCase("asc")){
                    sortList.add(Sort.Order.desc(sortInfo[0]));
                }else{
                    sortList.add(Sort.Order.asc(sortInfo[0]));
                }
            }


        }
        if (sortList.isEmpty()){
            return null;
        }
        return Sort.by(sortList);
    }
}
