package com.victoraster.StoreMananger;

import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.victoraster.StoreMananger.controllers.ProductController;
import com.victoraster.StoreMananger.exceptions.InvalidFields;
import com.victoraster.StoreMananger.exceptions.ResourceNotFoundException;
import com.victoraster.StoreMananger.services.ProductService;
import com.victoraster.StoreMananger.models.Product;
import com.victoraster.StoreMananger.repository.ProductRepository;

@AutoConfigureMockMvc
@SpringBootTest
public class TestProductController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @InjectMocks
    private ProductController productController;

    @Test
    public void listAllProductsTest() throws Exception {
        List<Product> products = new ArrayList<>();
        Product firstProduct = new Product();
        firstProduct.setId(1L);
        firstProduct.setName("WhitePoney");
        Product secondProduct = new Product();
        secondProduct.setId(2L);
        secondProduct.setName("Hand of midas");
        products.add(secondProduct);
        products.add(firstProduct);
        when(productService.getAllProducts()).thenReturn(products);

        mockMvc.perform(MockMvcRequestBuilders.get("/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("Hand of midas")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("WhitePoney")));
    }

    @Test
    public void getProdById() throws Exception {
        Product product = new Product();
        product.setId(2L);
        product.setName("Gundam X");

        when(productService.getProductById(2L)).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.get("/products/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Gundam X")));

    }

    @Test
    public void getProdById_Error_ResourceNotFound() throws Exception {
        String errorMessage = "Produto não encontrado";
        when(productService.getProductById(2L)).thenThrow(new ResourceNotFoundException(errorMessage));
        ;

        mockMvc.perform(MockMvcRequestBuilders.get("/products/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error", Matchers.is("Object Not Found")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("Produto não encontrado"))); // Top-level
                                                                                                                // field
    }

    @Test
    public void createProduct_Error_InvalidFields() throws Exception {
        String requestBody = "{\"name\": \"\"}"; // Provide a valid JSON with an empty name
    
        doThrow(new InvalidFields("Nome do produto vazio ou inválido")).when(productService).createProduct("");
    
        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error", Matchers.is("Invalid fields")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("Nome do produto vazio ou inválido")));
    }

}
