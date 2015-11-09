package com.edico.autoshop;

/**
 * Created by SennaTOR on 22.10.2015.
 */

public class Car {
    private String name;
    private String description;
    private int imageResourceId;

    // cars is an array of Cars
    public static final Car[] cars = {
        new Car("BMW 330i",
              "На протяжении 40 лет BMW 3 серии представляет собой идеальное воплощение спортивного седана.",
              R.drawable.bmw330),
        new Car("BMW M550d",
                "Инженеры BMW M воплотили свой уникальный опыт в модели BMW M550d xDrive с эксклюзивными, точно настраиваемыми системами трансмиссии, подвески и рулевого управления.",
                R.drawable.bmwm550d),
        new Car("BMW 750",
                "BMW 7 is the best car in the world.",
                R.drawable.bmw750)
    };

    // Each com.edico.autoshop.Car has a name, description, and an image resource
    private Car(String name, String description, int imageResourceId)
    {
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    public String getDescription()
    {
        return description;
    }

    public String getName()
    {
        return name;
    }

    public int getImageResourceId()
    {
        return imageResourceId;
    }

    public String toString() {
        return this.name;
    }
}
