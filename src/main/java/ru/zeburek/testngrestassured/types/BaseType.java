package ru.zeburek.testngrestassured.types;

import java.lang.reflect.Field;

public class BaseType extends Object {
    public boolean equals(Object object){
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        try {
            Field[] fields = this.getClass().getDeclaredFields();
            for (Field field : fields) {
//                Field objField = object.getClass().getDeclaredField(field.getName());
                if (!field.get(this).equals(field.get(object))) {
                    return false;
                }
            }
        } catch (IllegalAccessException e) {
            return false;
        }
        return true;
    }
}
