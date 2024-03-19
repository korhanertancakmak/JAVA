package CourseCodes.NewSections.Section_11_Abstraction_Interface.Course02_Abstract_Challenge;

//Part-10
/*
        And right away we have a problem, and you probably know what that is. This class needs to implement the abstract
    method, showDetails, so let me do that.
*/
//End-Part-10
public class ArtObject extends ProductForSale{
//Part-12
/*
        Notice it calls super, the super constructor, which is the constructor on the abstract class, and that has the
    3 parameters we declared on ProductForSale. Let's put some code in that overridden method.
*/
//End-Part-12

    public ArtObject(String type, double price, String description) {
        super(type, price, description);
    }

//Part-11
/*
        But this class still doesn't compile, for the same reason we saw in a previous course. We need to set up a constructor.
    And I'll generate that in the usual way.
*/
//End-Part-11

    @Override
    public void showDetails() {

        System.out.println("This " + type + " is a beautiful reproduction");
        System.out.printf("The price of the piece is $%6.2f %n", price);
        System.out.println(description);
    }

//Part-13
/*
        And this method's implementation is really individual to any product that subclasses ProductForSale. We print out
    some information and the price, and we directly access that price field, because we declared it protected, on ProductForSale.
    So that's it, that's all we need to do with that class. Now, back to the Store, I want an inventory of products, so
    I'll make that a private static field on that class.
*/
//End-Part-13
}
