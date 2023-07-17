package com.fabiankevin.orderserviceapp.infrastructure;

import com.fabiankevin.orderserviceapp.application.web.mapper.DefaultOrderMapper;
import com.fabiankevin.orderserviceapp.application.web.mapper.OrderMapper;
import com.fabiankevin.orderserviceapp.core.port.db.OrderRepository;
import com.fabiankevin.orderserviceapp.core.usecases.DefaultGetOrder;
import com.fabiankevin.orderserviceapp.core.usecases.DefaultPlaceOrder;
import com.fabiankevin.orderserviceapp.core.usecases.GetOrder;
import com.fabiankevin.orderserviceapp.core.usecases.PlaceOrder;
import com.fabiankevin.orderserviceapp.infrastructure.events.DefaultEventEmitter;
import com.fabiankevin.orderserviceapp.infrastructure.repositories.JPAOrderRepository;
import com.fabiankevin.orderserviceapp.infrastructure.repositories.SpringOrderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {

    @Bean
    public OrderRepository orderRepository(SpringOrderRepository springOrderRepository, DefaultOrderMapper orderMapper){
        return new JPAOrderRepository(springOrderRepository, orderMapper);
    }

    @Bean
    public PlaceOrder placeOrder(JPAOrderRepository orderRepository, DefaultEventEmitter defaultEventEmitter){
        return new DefaultPlaceOrder(defaultEventEmitter, orderRepository);
    }

    @Bean
    public GetOrder getOrder(JPAOrderRepository jpaOrderRepository){
        return new DefaultGetOrder(jpaOrderRepository);
    }
}
