import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args){
        List<Integer> numbers =
                new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));

        //void forEach(Consumer<? super T> action)
        numbers.stream().forEach(element -> System.out.print(element));
        System.out.println("");

        //Stream<T> filter(Predicate<? Super T> predicate)
        numbers.stream()
                .filter(element -> element%2==0 )
                .forEach(System.out::print);
        System.out.println("");

        //<R> Stream<R> map(Function<? super T,? extends R> mapper)
        numbers.stream()
                .map(element -> element*2 )
                .forEach(System.out::print);
        System.out.println("");

        numbers.stream()
                .map(element -> "number:"+element )
                .forEach(System.out::println);
        System.out.println("");

        numbers.stream()
                .map(element -> List.of(element*2, element*3) )
                .forEach(System.out::print);
        System.out.println("");

        //<R> Stream<R> flatMap(Function<? super T,? extends Stream<? extends R>> mapper)
        numbers.stream()
                .flatMap(element -> List.of(element*2, element*3).stream())
                .forEach(System.out::print);
        System.out.println("");

        numbers.stream()
                .flatMap(element -> Stream.of(element*2, element*3))
                .forEach(System.out::print);
        System.out.println("");

        //T reduce(T identity, BinaryOperator<T> accumulator)
        System.out.println(numbers.stream()
                .reduce(0,(e,carry) -> e+carry));
        //                     0   1,0           1
        //                         2,1           3
        //                         3,3           6


        //combining filter,map.reduce
        System.out.println(
                numbers.stream()
                        .filter(e -> e%2==0)
                        .map(e -> e*e)
                        .reduce(1,(e,carry) -> e*carry)
        );

        //IntStream mapToInt(ToIntFunction<? super T> mapper)
        //DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper)
        //LongStream mapToLong(ToLongFunction<? super T> mapper)
        System.out.println(
                numbers.stream()
                        .filter(e -> e%2==0)
                        .mapToInt(e -> e*e)
                        .sum());

        //Stream<T> peek(Consumer<? super T> action)
        //peek intermediate operator
        //forEach terminal operator
        //both works same if you want to see/debug what is happening btw
        //intermediate operation use peek
        numbers.stream()
                .filter(e -> e%2==0)
                .peek(e -> System.out.println("even:"+e))
                .map(e -> e+e)
                .forEach(e -> System.out.println("Square:"+e));

        //Object[] toArray()
        Object[] array = numbers.stream()
                .filter(e -> e % 2 == 0)
                .toArray();

        //default List<T> toList()
        List<Integer> list = numbers.stream()
                .filter(e -> e % 2 == 0)
                .map(e -> e + e)
                .toList();

        //<R,A> R collect(Collector<? super T,A,R> collector)
        Set<Integer> collect = numbers.stream()
                .filter(e -> e % 2 == 0)
                .map(e -> e + e)
                .collect(Collectors.toSet());
    }
}
