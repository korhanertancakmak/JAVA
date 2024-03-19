package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Generics.Course04_Generics_Challenge;

//Part-12
/*
        And the first thing I need to do, is make this generic, so I'll add a type parameter, and you might remember from
    requirements, that this class should only take Mappable types. In <>, I'll add T as the type, and extends Mappable
    after that.
*/
//End-Part-12

import java.util.ArrayList;
import java.util.List;

public class Layer<T extends Mappable>{

//Part-13
/*
        This sets an upper bound, meaning anything that is Mappable, can use this Layer class. It also means we can use
    methods on Mappable, without casting. Now, I want to set up a List of Mappable elements. I want this to be private,
    because I don't want any direct access to this list.
*/
//End-Part-13

    private List<T> layerElements;

//Part-14
/*
        This is going to use the reference type List, typed by the parameter type of this generic class, T. Next, I'll
    generate a constructor, which includes layer Elements.

                public Layer(List<T> layerElements) {
                    this.layerElements = layerElements;
                }

    So that gives us a constructor that will take a list of elements, but I actually want that argument to be an array.
    I'll change that List<T> to T[]. And I want to create the list as an ArrayList in the constructor, of type T.
*/
//End-Part-14

    public Layer(T[] layerElements) {
        this.layerElements = new ArrayList<T>(List.of(layerElements));
    }

//Part-15
/*
        This constructor lets the calling code pass an array, to create a new Layer. Now I want to include a method to add
    one or more Elements. If I use a variable argument, this can be a single method, which I'll call addElements. And the
    variable argument uses the T parameter, and simply calls addAll, a method on the list interface.
*/
//End-Part-15

    public void addElements(T... elements) {
        layerElements.addAll(List.of(elements));
    }

//Part-16
/*
        This method takes a list of data, not a variable argument, so I can generate a List, with List.Of method, with
    variable arguments. Next, I want a method called renderLayer.
*/
//End-Part-16

    public void renderLayer() {

        for (T element :layerElements) {
            element.render();
        }
    }

//Part-17
/*
        The for loop uses the type parameter. Because I've defined my type as extending Mappable,

            public class Layer<T extends MAppable> {}

    I can call the render method on this type parameter, the loop element, as I'm showing there. And that's a generic layer
    class. For the main method, I'm going to set up 3 parks, and 4 rivers, with their locations, from the tables in the
    description of the challenge.
*/
//End-Part-17
}
