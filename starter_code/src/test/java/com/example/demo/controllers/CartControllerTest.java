package com.example.demo.controllers;

import com.example.demo.TestUtils;
import com.example.demo.controllers.CartController;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.ItemRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.ModifyCartRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
public class CartControllerTest {
    private CartController cartController;
    private UserRepository userRepo = mock(UserRepository.class);
    private CartRepository cartRepo = mock(CartRepository.class);
    private ItemRepository itemRepo = mock(ItemRepository.class);

    private Cart cart = new Cart();
    private User user = new User();
    private Item item = new Item();

    @Before
    public void appSetup(){
        cartController = new CartController();
        TestUtils.injectObject(cartController, "cartRepository", cartRepo);
        TestUtils.injectObject(cartController, "itemRepository", itemRepo);
        TestUtils.injectObject(cartController, "userRepository", userRepo);

        user.setId(1L);
        user.setUsername("user");
        user.setPassword("password");
        user.setCart(cart);
        when(userRepo.findByUsername("user")).thenReturn(user);

        item.setId(0L);
        item.setName("apple");
        item.setPrice(BigDecimal.valueOf(1.01));
        when(itemRepo.findById(0L)).thenReturn(Optional.of(item));
    }

    @Test
    public void addToCart() {
        ModifyCartRequest request = new ModifyCartRequest();

        request.setItemId(0L);
        request.setQuantity(1);
        request.setUsername(user.getUsername());

        ResponseEntity<Cart> response = cartController.addTocart(request);
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(BigDecimal.valueOf(1.01), response.getBody().getTotal());
    }

    @Test
    public void addToCartUserNotFound(){
        ModifyCartRequest request = new ModifyCartRequest();
        request.setUsername("test");
        ResponseEntity<Cart> response = cartController.addTocart(request);
        assertNotNull(response);
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void removeFromCart() {
        ModifyCartRequest request = new ModifyCartRequest();
        request.setItemId(0L);
        request.setQuantity(2);
        request.setUsername(user.getUsername());
        ResponseEntity<Cart> response = cartController.addTocart(request);
        assertEquals(200, response.getStatusCodeValue());

        ModifyCartRequest cartRequestModified = new ModifyCartRequest();
        cartRequestModified.setItemId(0L);
        cartRequestModified.setQuantity(1);
        cartRequestModified.setUsername(user.getUsername());
        response = cartController.removeFromcart(cartRequestModified);
        assertEquals(BigDecimal.valueOf(1.01), response.getBody().getTotal());
    }

    @Test
    public void removeFromCartUsernotFound(){
        ModifyCartRequest request = new ModifyCartRequest();
        request.setUsername("test");
        request.setItemId(0L);

        ResponseEntity<Cart> response = cartController.removeFromcart(request);
        assertNotNull(response);
        assertEquals(404, response.getStatusCodeValue());

    }
}
