package com.redsky.myretail;

import com.redsky.myretail.exceptions.ProductNotFoundException;
import com.redsky.myretail.models.Products;
import com.redsky.myretail.repositories.ProductsRepository;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductsController {

    
    @Autowired
    private ProductsRepository repository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Products> getAllProducts() {
        return repository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Products getProductById(@PathVariable("id") String id) {

        long _id;
        try {
            _id = Long.parseLong(id);
        }
        catch (NumberFormatException e) {
            throw new ProductNotFoundException("Product " + id + " not found");
        }

        String name = getProductName(id);

        Products result = repository.findById(_id);
        if (result == null || name == null || name.isEmpty()) {
            throw new ProductNotFoundException("Product " + id + " not found");
        }
        result.setName(name);
        return result;

    }

    private String getProductName(@PathVariable("id") String id) {
        String urlString = "https://redsky.target.com/v2/pdp/tcin/" + id +
                    "?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics," +
                    "question_answer_statistics";
        System.out.println("URL is: " + urlString);
        String name = null;
        try {
            //TODO Use HTTPCLIENT to handle redirects
            //HttpClient instance = HttpClientBuilder.create()
            //.setRedirectStrategy(new LaxRedirectStrategy()).build();
            String content = IOUtils.toString(new URL(urlString), Charset.forName("UTF-8")).trim();
            if (content.length() > 0) {
                JSONObject json = new JSONObject(content);
                JSONObject product = json.getJSONObject("product");
                JSONObject item = product.getJSONObject("item");
                JSONObject product_description = item.getJSONObject("product_description");
                name = product_description.getString("title");
                System.out.println("name is: " + name);
            }

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return name;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateProductById(@PathVariable("id") long id, @Valid @RequestBody Products Products) {
        Products current = repository.findById(id);
        if (current == null ) {
            throw new ProductNotFoundException("Product " + Long.toString(id) + " not found");
        }
        ObjectId _id = new ObjectId(current.get_id());
        Products.set_id(_id);
        repository.save(Products);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Products createProduct(@Valid @RequestBody Products Products) {
        Products.set_id(ObjectId.get());
        repository.save(Products);
        return Products;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable long id) {
        repository.delete(repository.findById(id));
    }

}

