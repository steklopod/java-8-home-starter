package ru.stdpr.fc.streamtests.objectstreams.another;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
class Person {

    private String name;
    private int age;

}
