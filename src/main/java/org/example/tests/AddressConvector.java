package org.example.tests;

import com.google.gson.Gson;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.List;

@Converter // клас буде конвертувати String in Varchar за допомогою Gson і навпаки
public class AddressConvector implements AttributeConverter<List<String>, String> {

    AddressConvector(){
        System.out.println("NEW CONVECTOR CREATED!");
    }


    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        // беремо обьект і скармлюємо його в Gson()

        return new Gson().toJson(attribute);
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        return new Gson().fromJson(dbData, List.class);
    }
}

// наше завдання зберегти ліст (список) в одне поле в таблицю List<String> переводимо в рядок і навпаки
// рядок у список

