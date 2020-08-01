package sia.tacocloud.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.DirtiesContext;
import sia.tacocloud.domain.Order;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void Save_Order_PersistedOrder() {
        orderRepository.save(order());
        Specification<Order> spec = (root, query, cb) -> cb.equal(root.get("name"), "Custom Order");
        long count = orderRepository.count(spec);
        assertEquals(1, count);
    }

    @Test
    public void Get_PersistedOrder_OrderByName() {
        Order order = orderRepository.save(order());
        List<Order> orders = orderRepository.findByName("Custom Order");
        assertNotNull(orders);
        assertFalse(orders.isEmpty());
        assertEquals(1, orders.size());
        assertEquals(order.getId(), orders.get(0).getId());
    }

    @Test
    public void Get_PersistedOrder_OrderByZip() {
        Order order = orderRepository.save(order());
        List<Order> orders = orderRepository.findByZip("00000");
        assertNotNull(orders);
        assertFalse(orders.isEmpty());
        assertEquals(1, orders.size());
        assertEquals(order.getId(), orders.get(0).getId());
    }

    @Test
    public void Get_PersistedOrders_OrdersByZipAndBetweenDates() {
        Order order1 = orderRepository.save(order());
        Order order2 = orderRepository.save(order());
        Date startDate = new Date(Instant.now().minus(1, ChronoUnit.DAYS).toEpochMilli());
        Date endDate = new Date(Instant.now().plus(1, ChronoUnit.DAYS).toEpochMilli());
        List<Order> orders = orderRepository.readOrdersByZipAndPlacedAtBetween("00000", startDate, endDate);
        assertNotNull(orders);
        assertFalse(orders.isEmpty());
        assertEquals(2, orders.size());
        assertTrue(isPresentInList(order1.getId(), orders));
        assertTrue(isPresentInList(order2.getId(), orders));

        startDate = new Date(Instant.now().plus(1, ChronoUnit.DAYS).toEpochMilli());
        endDate = new Date(Instant.now().plus(3, ChronoUnit.DAYS).toEpochMilli());
        orders = orderRepository.readOrdersByZipAndPlacedAtBetween("00000", startDate, endDate);
        assertNotNull(orders);
        assertTrue(orders.isEmpty());
    }

    @Test
    public void Get_PersistedOrders_OrdersByNameAndCityIgnoringCase() {
        Order order1 = orderRepository.save(order());
        Order order2 = orderRepository.save(order());
        List<Order> orders = orderRepository.findByNameAndCityAllIgnoringCase("cUsToM oRDer", "any CITY");
        assertNotNull(orders);
        assertFalse(orders.isEmpty());
        assertEquals(2, orders.size());
        assertTrue(isPresentInList(order1.getId(), orders));
        assertTrue(isPresentInList(order2.getId(), orders));

        orders = orderRepository.findByNameAndCityAllIgnoringCase("Non existent Name", "any CITY");
        assertNotNull(orders);
        assertTrue(orders.isEmpty());
    }

    @Test
    public void Get_PersistedOrders_OrdersByCitySortedByName() {
        Order newOrder = order();
        newOrder.setName("La Paz");
        Order order1 = orderRepository.save(newOrder);
        Order order2 = orderRepository.save(order());
        List<Order> orders = orderRepository.findByCityOrderByName("Any City");
        assertNotNull(orders);
        assertFalse(orders.isEmpty());
        assertEquals(2, orders.size());
        assertEquals(order2.getId(), orders.get(0).getId());
        assertEquals(order1.getId(), orders.get(1).getId());
    }

    @Test
    public void Get_PersistedOrder_OrderByCity() {
        Order newOrder = orderRepository.save(order());
        Optional<Order> order = orderRepository.selectOrderFromCity("Any City");
        assertTrue(order.isPresent());
        assertEquals(newOrder.getId(), order.get().getId());
        assertEquals(newOrder.getCity(), order.get().getCity());

        order = orderRepository.selectOrderFromCity("Non existent City");
        assertTrue(order.isEmpty());
    }

    private boolean isPresentInList(long orderId, List<Order> orders) {
        return orders.stream().anyMatch(order -> orderId == order.getId());
    }

    private Order order() {
        return Order.builder()
                .name("Custom Order")
                .street("Any Street")
                .city("Any City")
                .state("Any State")
                .zip("00000")
                .ccNumber("4283410013958039")
                .ccExpiration("08/30")
                .ccCVV("123")
                .build();
    }
}
