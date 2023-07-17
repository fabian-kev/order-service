package com.fabiankevin.orderserviceapp.application.web;

import com.fabiankevin.orderserviceapp.application.web.dto.AmountDto;
import com.fabiankevin.orderserviceapp.application.web.dto.request.ItemRequest;
import com.fabiankevin.orderserviceapp.application.web.dto.request.PlaceOrderRequest;
import com.fabiankevin.orderserviceapp.application.web.mapper.OrderMapper;
import com.fabiankevin.orderserviceapp.core.usecases.GetOrder;
import com.fabiankevin.orderserviceapp.core.usecases.PlaceOrder;
import com.fabiankevin.restapistarter.WebAutoConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
@ExtendWith(SpringExtension.class)
@Import({WebAutoConfig.class})
public class OrderControllerTes {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OrderMapper orderMapper;

    @MockBean
    private PlaceOrder placeOrder;

    @MockBean
    private GetOrder getOrder;

    private UUID orderId = UUID.randomUUID();

    @Test
    void placeOrder_thenItShouldSucceed() throws Exception {

        PlaceOrderRequest request = new PlaceOrderRequest(
                UUID.randomUUID(),
                List.of(new ItemRequest(
                        1,
                        "SRS",
                        "Sardines",
                        "Spicy Sardines",
                        new AmountDto("PHP", BigDecimal.valueOf(200)),
                        UUID.randomUUID()
                        )),
                "some note",
                "PHP"
        );

        when(placeOrder.execute(any()))
                .thenReturn(orderId);

        mvc.perform(MockMvcRequestBuilders
                .post("/v1/orders")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("order_id").value(orderId.toString()));

    }
}
