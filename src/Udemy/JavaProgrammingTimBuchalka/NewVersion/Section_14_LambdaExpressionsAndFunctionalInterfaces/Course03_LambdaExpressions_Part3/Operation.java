package CourseCodes.NewSections.Section_14_LambdaExpressionsAndFunctionalInterfaces.Course03_LambdaExpressions_Part3;

//Part-2
/*
        I'll make it a generic interface, meaning I'll include a type parameter. For this type, I won't include any bounds,
    so this interface can be used with any type. I'll add a single abstract method, called "operate" that returns T, and
    takes 2 values, also type T, so value 1 and value 2. This means this method takes 2 arguments of the same type, and
    returns a value, also the same type as the values. This interface is a functional interface. It has one single abstract
    method. I can verify that by adding an annotation statement above it, "@FunctionalInterface". And you can see the code
    still compiles, so I know for sure this is a functional interface. I'll talk more formally about annotations later.
    I'm using this one now, because it's best practice to include it, and it'll also help you verify that what you intended
    it to be, is really what it is. I showed you earlier that this can get confusing, if you're extending existing interfaces.
    This annotation also informs future developers what your intentions were, and warns them not to include additional
    abstract methods, which could break code that uses this interface as a target for lambda expressions. Before I continue,
    I want to make sure you have an IntelliJ feature toggled on, that will help you understand lambda expressions better.
    Going to settings, under the File Menu. Open the Editor node, and under that open the general node, and look for Gutter
    Icons. Select that. About in the center of the list, you'll see Lambda listed there, and you want to make sure that's
    checked. You can see that mine was unchecked, so I'll check it now. You'll notice this is a pink icon with the greek
    lambda symbol in it. I'll show you what this looks like in the IntelliJ editor in just a moment. But first, I want
    to create a public static generic method on my Main class,
*/
//End-Part-2

@FunctionalInterface
public interface Operation<T> {

    T operate(T value1, T value2);
}
