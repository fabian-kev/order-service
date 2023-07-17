package com.fabiankevin.orderserviceapp.core.usecases;

import com.fabiankevin.domainstarter.domain.Amount;
import com.fabiankevin.orderserviceapp.core.EventEmitter;
import com.fabiankevin.orderserviceapp.core.domain.Item;
import com.fabiankevin.orderserviceapp.core.domain.Order;
import com.fabiankevin.orderserviceapp.core.domain.OrderStatus;
import com.fabiankevin.orderserviceapp.core.domain.value.Currency;
import com.fabiankevin.orderserviceapp.core.domain.value.Note;
import com.fabiankevin.orderserviceapp.core.domain.value.Quantity;
import com.fabiankevin.orderserviceapp.core.exceptions.ItemAlreadyExistsException;
import com.fabiankevin.orderserviceapp.core.port.db.OrderRepository;
import com.fabiankevin.orderserviceapp.core.usecases.inbound.ItemCommand;
import com.fabiankevin.orderserviceapp.core.usecases.inbound.PlaceOrderCommand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DefaultPlaceOrderTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private EventEmitter emitter;

    @InjectMocks
    private DefaultPlaceOrder defaultPlaceOrder;

    private UUID orderId = UUID.randomUUID();

    @Test
    void placeOrder_thenSucceed() {
        PlaceOrderCommand command = placeOrderCommand();


        Order order = Order.builder()
                .asPendingOrder()
                .customerId(UUID.randomUUID())
                .id(orderId)
                .note(new Note("note"))
                .currency(Currency.PHP())
                .build();

        order.addItem(Item.builder()
                .id(UUID.randomUUID())
                .code("CDE")
                .quantity(new Quantity())
                .description("descriptiuon")
                .name("CODE")
                .productId(UUID.randomUUID())
                .unitPrice(Amount.of("PHP", 200.0))
                .build());

        when(orderRepository.save(any()))
                .thenReturn(order);

        UUID orderID = defaultPlaceOrder.execute(command);

        assertNotNull(orderID, "order id");

        ArgumentCaptor<Order> argumentCaptor = ArgumentCaptor.forClass(Order.class);
        verify(orderRepository).save(argumentCaptor.capture());
        Order orderArgument = argumentCaptor.getValue();
        assertOrder(command, orderArgument, OrderStatus.PENDING);

        verify(emitter).emit(any(Order.class));
    }

    @Test
    void addItem_givenDuplicateItem_thenThrowException(){
        ItemCommand itemCommand = itemRequest();

        PlaceOrderCommand command = new PlaceOrderCommand(
                UUID.randomUUID(),
                List.of(itemCommand, itemCommand),
                "some note",
                "PHP"
        );

        assertThrows(ItemAlreadyExistsException.class,
                () -> {
                    defaultPlaceOrder.execute(command);
                }, "item already exists");


    }

    private static void assertOrder(PlaceOrderCommand command, Order order, OrderStatus orderStatus) {
        double totalAmount = command.items().stream()
                .map(ItemCommand::unitPrice)
                .mapToDouble(amount -> amount.getValue().doubleValue())
                .sum();
        assertEquals(command.customerId(), order.getCustomerId(), "customer id");
        assertEquals(command.note(), order.getNote(), "note");
        assertEquals(Currency.PHP().value(), order.getCurrency(), "currency");
        assertEquals(totalAmount, order.getTotalAmount().getValue().doubleValue(), "total amount");
        assertEquals(orderStatus, order.getStatus(), "status");

        //items
        ItemCommand itemCommand = command.items().get(0);
        Item item = order.getItems().get(0);

        assertEquals(itemCommand.code(), item.getCode(), "code");
        assertEquals(itemCommand.description(), item.getDescription(), "description");
        assertEquals(itemCommand.name(), item.getName(), "name");
        assertEquals(itemCommand.quantity(), item.getQuantity().getValue(), "quantity");
        assertEquals(itemCommand.unitPrice(), item.getUnitPrice(), "unit price");
    }

    private static PlaceOrderCommand placeOrderCommand() {
        return new PlaceOrderCommand(
                UUID.randomUUID(),
                List.of(itemRequest()),
                "some note",
                "PHP"
        );
    }

    private static ItemCommand itemRequest() {
        return new ItemCommand(
                1,
                "GAR_PEANUTS",
                "Garlic Flavor Peanuts",
                "Garlic flavor peanuts",
                Amount.of("PHP", 200.0),
                UUID.randomUUID()
        );
    }

}
