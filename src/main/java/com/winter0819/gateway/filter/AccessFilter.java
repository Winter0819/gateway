package com.winter0819.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Component
public class AccessFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx =  RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String userid = request.getParameter("userid");
        if( ! Objects.equals("1",userid) ){
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(500);
            return null;
        }
        return null;
    }
}
