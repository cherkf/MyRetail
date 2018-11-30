package com.redsky.myretail.models;


import com.mongodb.BasicDBObject;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Products {

    @Id
    public ObjectId _id;

    public long id;
    public String name;
    public BasicDBObject current_price;

    // Constructors
    public Products() {

    }

    public Products(long id, String name, BasicDBObject current_price) {
        this.id = id;
        this.current_price = current_price;
        this.name = name;
    }

    // ObjectId needs to be converted to string
    public String get_id() { return _id.toHexString(); }
    public void set_id(ObjectId _id) { this._id = _id; }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }


    public Object getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(long value, String current_currency) {
        BasicDBObject current_price = new BasicDBObject();

        current_price.append("value", value);
        current_price.append("current_currency", current_currency);
        this.current_price = current_price;
    }

}
