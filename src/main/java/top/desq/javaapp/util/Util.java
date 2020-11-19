package top.desq.javaapp.util;


import org.springframework.lang.Nullable;
import top.desq.javaapp.model.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class Util {

    public static final List<Auto> AUTO = Arrays.asList(
            //   this(null, brand, model, body, color, power, fuel, dateTime);
            new Auto(Brand.HONDA, "Civic", BodyStyle.SEDAN, Color.BLACK, 140, FuelType.GASOLINE, LocalDateTime.now()),
            new Auto(Brand.MAZDA, "3", BodyStyle.HATCHBACK, Color.RED, 115, FuelType.GASOLINE, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0)),
            new Auto(Brand.TOYOTA, "Corolla", BodyStyle.COUPE, Color.BLUE, 130, FuelType.DIESEL, LocalDateTime.of(2020, Month.JANUARY, 30, 11, 5)),
            new Auto(Brand.VW, "B5", BodyStyle.COUPE, Color.BLUE, 130, FuelType.DIESEL, LocalDateTime.of(2020, Month.JANUARY, 30, 11, 5))
    );

    public static <T extends Comparable<T>> boolean isBetweenHalfOpen(T value, @Nullable T start, @Nullable T end) {
        return (start == null || value.compareTo(start) >= 0) && (end == null || value.compareTo(end) < 0);
    }

}
