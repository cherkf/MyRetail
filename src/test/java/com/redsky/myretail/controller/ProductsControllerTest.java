package com.redsky.myretail.controller;

import com.mongodb.BasicDBObject;
import com.redsky.myretail.ProductsController;
import com.redsky.myretail.models.Products;
import com.redsky.myretail.repositories.ProductsRepository;
import org.bson.types.ObjectId;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductsController.class, secure = false)
public class ProductsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductsRepository productsRepository;

    String json = "{\"value\": 13.49,\"currency_code\":\"USD\"}";
    Object current_price = BasicDBObject.parse(json);
    ObjectId _id = new ObjectId("5c02b7669021a937c7194a74");
    Products mockProduct = new Products(_id, 13860428, null, (BasicDBObject) current_price);

    @Test
    public void getProductById() throws Exception {
        Mockito.doReturn(mockProduct).when(productsRepository).findById(Mockito.anyLong());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/products/13860428").accept(
                MediaType.APPLICATION_JSON);


        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{\"id\":13860428,\"name\":\"The Big Lebowski (Blu-ray)\"," +
                "\"current_price\":{\"value\":13.49,\"currency_code\":\"USD\"}}";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void getProductById_NotFound() throws Exception {
        Mockito.doReturn(mockProduct).when(productsRepository).findById(Mockito.anyLong());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/products/wrong_id").accept(
                MediaType.APPLICATION_JSON);


        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(result.getResponse().getStatus(), 404);
    }

    @Test
    public void updateProductById() throws Exception {
        Mockito.doReturn(mockProduct).when(productsRepository).findById(Mockito.anyLong());

        JSONObject body = new JSONObject("{\n" +
                "    \"id\": 13860428,\n" +
                "    \"name\": \"The Big Lebowski (Blu-ray)\",\n" +
                "    \"current_price\": {\n" +
                "        \"value\": 355,\n" +
                "        \"currency_code\": \"USD\"\n" +
                "    }\n" +
                "}");


        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(
                "/products/13860428").accept(
                MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(body));


        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(result.getResponse().getStatus(), 200);

    }

}
