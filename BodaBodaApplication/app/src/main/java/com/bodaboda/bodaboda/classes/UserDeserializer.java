package com.bodaboda.bodaboda.classes;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class UserDeserializer implements JsonDeserializer<User> {
    @Override
    public User deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
            throws JsonParseException
    {
        JsonObject jo;
        if(je.isJsonObject()){
            jo = (JsonObject) je;
        }else throw null;
        System.out.println("TUsam");
        System.out.println(jo.get("taxiPrice"));
        jo.addProperty("taxiPrice", "null");
        System.out.println(jo.get("taxiPrice"));
        return new Gson().fromJson(jo, type);

    }
}