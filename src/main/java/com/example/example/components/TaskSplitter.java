package com.example.example.components;

import com.example.example.model.SavantaTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.integration.annotation.Splitter;
import org.springframework.stereotype.Component;


import java.lang.reflect.Type;
import java.util.List;

@Component
public class TaskSplitter<T> {


    private Gson gson;

//    static Type getType(String typeName){
//        switch (typeName){
//            case SavantaTask.class.gre
//                return new TypeToken<List<SavantaTask>() {}.getType();
//            case "mypackage2.MyThing1":
//                return new TypeToken<MyThing2>() {}.getType();
//            default:
//                throw new RuntimeException("Unsupported type: " + typeName);
//        }
//    }

    @Splitter
    public List<T> splitTasks(String jsonInput,Class<T> clazz) {
//       new Type(clazz.getTypeName())

        Type collectionType = new TypeToken<List<T>>(){}.getType();
        return gson.fromJson(jsonInput, collectionType);
    }
}
