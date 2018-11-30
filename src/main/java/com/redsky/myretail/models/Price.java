package com.redsky.myretail.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.schema.TypedJsonSchemaObject;

@Document
public class Price {
//    "current_price":
//    { "value": 13.49,
//            "currency_code":"USD"
//    }

    @Id
    public ObjectId _id;

    public long value ;

    public String currency_code;

    // Constructors
    public Price() { };

    public String get_id() { return _id.toHexString(); }
    public void set_id(ObjectId _id) { this._id = _id; }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }





}
