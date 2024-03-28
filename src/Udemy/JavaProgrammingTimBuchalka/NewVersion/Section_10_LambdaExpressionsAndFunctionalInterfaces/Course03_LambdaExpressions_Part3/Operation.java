package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_10_LambdaExpressionsAndFunctionalInterfaces.Course03_LambdaExpressions_Part3;

@FunctionalInterface
public interface Operation<T> {

    T operate(T value1, T value2);
}
