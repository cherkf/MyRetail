package com.redsky.myretail;

import com.redsky.myretail.models.Products;
import com.redsky.myretail.repositories.ProductsRepository;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
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
    public Products getProductById(@PathVariable("id") long id) {
        String urlString = "https://redsky.target.com/v2/pdp/tcin/"+ Long.toString(id)+
                "?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,"+
                "question_answer_statistics";
        System.out.println("URL is: " + urlString);
        String name = null;
        try {
            //TODO Use HTTPCLIENT to handle redirects
            JSONObject json = new JSONObject(IOUtils.toString(new URL(urlString), Charset.forName("UTF-8")).trim());
            JSONObject product = json.getJSONObject("product");
            JSONObject item = product.getJSONObject("item");
            JSONObject product_description = item.getJSONObject("product_description");
            name = product_description.getString("title");
            System.out.println("name is: " + name);
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        Products result = repository.findById(id);
        result.setName(name);
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void modifyProductById(@PathVariable("id") long id, @Valid @RequestBody Products Products) {
        Products.setId(id);
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

    private static String getContentFromUrl(String urlToRead) {
        StringBuilder result = new StringBuilder();
        URL url = null;
        try {
            url = new URL(urlToRead);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                System.out.print("Line here: " + line);
                result.append(line);
            }
            rd.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }
}

