package com.anjukakoralage.layoutindexdemo.helper;

import com.anjukakoralage.layoutindexdemo.model.UserDetails;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by anjukakoralage on 27,September,2019
 */
class MyDeserializer<T> implements JsonDeserializer<T>
{
    @Override
    public T deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
            throws JsonParseException
    {
        // Get the "content" element from the parsed JSON
        JsonElement content = je.getAsJsonObject().get("data");

        // Deserialize it. You use a new instance of Gson to avoid infinite recursion
        // to this deserializer
        return new Gson().fromJson(content, type);

    }

}
