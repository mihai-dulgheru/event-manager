package main.interfaces;

import main.abstractClasses.AModel;

public interface IPrint {
    default void print(AModel model) {
        System.out.println(model);
    }
}
