package org.example.effectivejava.chapter2.item4;

import java.util.OptionalDouble;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkArgument;

public class ItemUtils {

    private ItemUtils() {
        throw new RuntimeException("Class cannot be instantiated");
    }

    public static int sumElements(Integer... list) {
        checkArgument(list != null, "List cannot be null");
        Stream<Integer> finalList = getIntegerStream(list);
        return finalList.mapToInt(Integer::intValue).sum();
    }

    public static double average(Integer... list) {
        checkArgument(list != null, "List cannot be null");
        Stream<Integer> stream = getIntegerStream(list);
        OptionalDouble optionalDouble = stream.mapToInt(Integer::intValue).average();
        if (optionalDouble.isPresent()) {
            return optionalDouble.getAsDouble();
        } else {
            return 0.0;
        }
    }

    private static Stream<Integer> getIntegerStream(Integer[] list) {
        Stream.Builder<Integer> builder = Stream.builder();
        for (int i : list) {
            builder.add(i);
        }
        return builder.build();
    }
}
