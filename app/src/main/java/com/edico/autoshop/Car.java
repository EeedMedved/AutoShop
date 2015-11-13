package com.edico.autoshop;

/**
 * Created by SennaTOR on 22.10.2015.
 */

public class Car {
    private String name;
    private String description;
    private int imageResourceId;
    private boolean favorite;

//    // cars is an array of Cars
//    public static final Car[] cars = {
//        new Car("BMW 330i",
//              "На протяжении 40 лет BMW 3 серии представляет собой идеальное воплощение спортивного седана.",
//              R.drawable.bmw330,
//                ),
//        new Car("BMW M550d",
//                "Инженеры BMW M воплотили свой уникальный опыт в модели BMW M550d xDrive с эксклюзивными, точно настраиваемыми системами трансмиссии, подвески и рулевого управления.",
//                R.drawable.bmwm550d),
//        new Car("BMW 750",
//                "BMW 7 is the best car in the world.",
//                R.drawable.bmw750)
//    };

    public Car()
    {}

    // Each com.edico.autoshop.Car has a name, description, and an image resource
    private Car(String name, String description, int imageResourceId, boolean favorite)
    {
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
        this.favorite = favorite;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageResourceId()
    {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public boolean getFavorite() { return this.favorite;}

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String toString() {
        return this.name;
    }
}
