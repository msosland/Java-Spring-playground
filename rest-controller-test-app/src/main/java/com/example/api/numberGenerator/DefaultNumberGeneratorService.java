package com.example.api.numberGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.JdkIdGenerator;

/**
 * Created by msosl on 12/28/16.
 */
@Service
public class DefaultNumberGeneratorService implements NumberGeneratorService {
    @Autowired
    JdkIdGenerator jdkIdGenerator;

    @Override
    public String generateNumber() {
        System.out.println(jdkIdGenerator.generateId());
        String number = jdkIdGenerator.generateId().toString();

        return number;
    }

}
