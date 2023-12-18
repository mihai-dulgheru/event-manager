package interfaces;

import abstractClasses.AbstractModel;

public interface Printable {
    default void print(AbstractModel model) {
        System.out.println(model);
    }
}
