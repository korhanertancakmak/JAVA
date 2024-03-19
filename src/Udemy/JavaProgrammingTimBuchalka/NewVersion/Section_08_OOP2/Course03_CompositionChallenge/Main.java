package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_08_OOP2.Course03_CompositionChallenge;

/*
Course-54

                                          The Composition Challenge

        In this challenge, you need to create an application for controlling a smart kitchen. Your smart kitchen will have
    several appliances. Your appliances will be Internet Of Things (IoT) devices, which can be programmed. For example,
    you might do stuff in your kitchen in the morning, in a rush to get to work, and then expect your smart kitchen appliances
    to run automatically at a later time.
                                                                ____________________________________________
                                                               |Refrigerator =>                             |
                                                               |            hasWorkToDo: boolean            |
                                                     |---------|            ----------------------          |
                                                     |         |            orderFood()                     |
         ___________________________________         |         |____________________________________________|
         |SmartKitchen =>                  |         |          ____________________________________________
         |        brewMaster:CoffeeMaker   |         |         |DishWasher =>                               |
         |        dishWasher:DishWasher    |         |         |            hasWorkToDo: boolean            |
         |        iceBox:Refrigerator      |<<=================|            ----------------------          |
         |---------------------------------|         |         |            doDishes()                      |
         |        addWater()               |         |         |____________________________________________|
         |        pourMilk()               |         |          ____________________________________________
         |        loadDishwasher()         |         |         |CoffeeMaker =>                              |
         |        setKitchenState(boolean, |         |         |            hasWorkToDo: boolean            |
         |                        boolean, |         |---------|            ----------------------          |
         |                        boolean) |                   |            brewCoffee()                    |
         |        doKitchenWork()          |                   |____________________________________________|
         |_________________________________|

        It's your job to write the code, to enable your Smart Kitchen application, to execute certain jobs. Methods on your
    SmartKitchen class, will determine what work needs to be done:

  - addWater() will set the Coffee Maker's hasWorkToDo field to true.
  - pourMilk() will set Refrigerator's hasWorkTodo to true.
  - loadDishwasher() will set the hasWorkToDo flag to true, on that appliance.

    Alternatively, you could have a single method, called setKitchenState, that takes 3 boolean values, which could combine
    the 3 methods above.

        To execute the work needed to be done by the appliances, you'll implement this in 2 ways:
  - First, your application will access each appliance (by using a getter), and execute a method. The appliance methods
    are orderFood() on Refrigerator, doDishes() on DishWasher, and brewCoffee() on CoffeeMaker. These methods should check
    the hasWorkToDo flag, and if true, print a message out, about what work is being done.
  - Second, your application won't access the appliances directly. It should call doKitchenWork(), which delegates the work,
    to any of its appliances.

        You'll have to create the appliances as classes, with their own methods, as shown on the diagram, and then compose
    your smart kitchen with these appliances.

    Answer:

        Now the first thing we'll do is to create the SmartKitchen class and to add all the attributes, even though we
    haven't created the individual appliance classes yet.

                        public class SmartKitchen {
                            private CoffeeMaker brewMaster;
                            private Refrigerator iceBox;
                            private DishWasher dishWasher;
                        }

    Ok, that's not going to compile, until we create these classes. And we're just going to add these classes in the same
    source file. Now, we could have had all of our appliance inherit from a base class. That's one valid way to do this.
    For simplicity, I'm not going to include inheritance in my solution, and this means my appliances won't be extending
    any class, other than the implied one, Object.

        Ok, we know CoffeeMaker needs one attribute, hasWorkToDo, a boolean. And we'll add the setter method for this boolean
    flag:
                        class CoffeeMaker {
                            private boolean hasWorkToDo;

                            public void setHasWorkToDo(boolean hasWorkToDo) {
                                this.hasWorkToDo = hasWorkToDo;
                            }
                        }

    And now, we'll add the method we want to run, if there's work to do. For the coffee maker appliance, this is the brew
    Coffee method. And first we'll make sure there's work to do, by checking the attribute hasWorkToDo. If that's true,
    we'll just print the text, "Brewing Coffee", and set HasWorkToDo to false after.

                        public void brewCoffee() {
                            if (hasWorkToDo) {
                                System.out.println("Brewing Coffee");
                                hasWorkToDo = false;
                            }
                        }

    Ok, that's the CoffeeMaker class. Now, we'll just copy and paste that entire class, and we'll rename it to Refrigerator.
    And we'll change the method name from brewCoffee, to orderFood. And then we want to change the text in the println
    statement, to say, "Ordering Food", this time.

                        class Refrigerator {
                            private boolean hasWorkToDo;
                            public void setHasWorkToDo(boolean hasWorkToDo) {
                                this.hasWorkToDo = hasWorkToDo;
                            }
                            public void orderFood() {
                                if (hasWorkToDo) {
                                    System.out.println("Ordering Food");
                                    hasWorkToDo = false;
                                }
                            }
                        }

    And finally, for the last appliance, we'll paste the CoffeeMaker class again. We'll rename it this time to Dishwasher.
    We'll change the method name from brewCoffee, to doDishes. And we'll change the text, being printed, to "Washing Dishes".

                        class DishWasher {
                            private boolean hasWorkToDo;
                            public void setHasWorkToDo(boolean hasWorkToDo) {
                                this.hasWorkToDo = hasWorkToDo;
                            }
                            public void doDishes() {
                                if (hasWorkToDo) {
                                    System.out.println("Washing Dishes");
                                    hasWorkToDo = false;
                                }
                            }
                        }

    Ok, now that we have our 3 appliances, we can get back to the Smart Kitchen class, which we can see now compiles. Next
    we want to add a constructor. When we create a new SmartKitchen, we're just going to instantiate our own set of Appliances,
    by creating our own no arguments constructor. This means that any code creating a SmartKitchen, won't have to create
    the appliances individually, like we did with the personal computer example in the previous course. In this case, we're
    saying the kitchen comes with a standard set of appliances, when it's created:

                        public SmartKitchen() {
                            brewMaster = new CoffeeMaker();
                            iceBox = new Refrigerator();
                            dishWasher = new DishWasher();
                        }

    And now let's generate the getters:

                        public CoffeeMaker getBrewMaster() {
                            return brewMaster;
                        }
                        public Refrigerator getIceBox() {
                            return iceBox;
                        }
                        public DishWasher getDishWasher() {
                            return dishWasher;
                        }

    And that does it for the Smart Kitchen. Now, let's go back to the Main class, main method. We'll create a new instance
    of a SmartKitchen. And then we'll use the getter methods, to call specific methods on the appliances, we know are in
    the kitchen:

                        SmartKitchen kitchen = new SmartKitchen();
                        kitchen.getDishWasher().doDishes();
                        kitchen.getIceBox().orderFood();
                        kitchen.getBrewMaster().brewCoffee();

    Ok, that's the set up. If we run that though, we're not going to get any output. That's because all the methods expect
    that the hasWorkToDo flag, on the appliance, to be set somehow. Before these appliances can be ready to do something,
    we want to put a dish in the dishwasher, use up some products in the fridge, or put water and coffee grounds in the
    coffee maker, for example. We'll use the getter methods on kitchen, to emulate that work first, to set some of these
    appliances into a state of readiness.

                        kitchen.getDishWasher().setHasWorkToDo(true);
                        kitchen.getIceBox().setHasWorkToDo(true);
                        kitchen.getBrewMaster().setHasWorkToDo(true);

    Now, we've done some stuff in the kitchen, and our appliances are awake of work to be done. And we've also called each
    appliance individually, to do the work on each. Running this code,

                        Washing Dishes
                        Ordering Food
                        Brewing Coffee

    we can see that our Smart Appliances ran. That was calling methods outside of the Kitchen, with code that is aware of
    the different appliances in the kitchen. We used the get methods, to call methods on specific parts of the kitchen.

        Now, let's create 2 more methods on SmartKitchen, that hide some of the details from the calling code. We'll just
    give the calling code a method to pass some boolean flags to our kitchen, and if the kitchen has a smart appliance
    that cares about that flag, we'll set that flag.

                        public void setKitchenState(boolean coffeeFlag, boolean fridgeFlag,
                                boolean dishWasherFlag) {
                            brewMaster.setHasWorkToDo(coffeeFlag);
                            iceBox.setHasWorkToDo(fridgeFlag);
                            dishWasher.setHasWorkToDo(dishWasherFlag);
                        }

    Ok, here we have a single method, that can set some of the appliance's hasWorkToDo boolean flag property. Now, let's
    create a method on SmartKitchen, called doKitchenWork. This method hides what work will be done, from the calling code,
    which doesn't really care about all the individual work items. And we want to add this method, which calls each appliance's
    method, that does the work.

                        public void doKitchenWork() {
                            brewMaster.brewCoffee();
                            iceBox.orderFood();
                            dishWasher.doDishes();
                        }

    Ok, now, going back to the main method, we'll comment out the code that sets the state on each appliance, and then calls
    each appliance's work method. And we'll instead call the one method on kitchen, to set some of the appliance's readiness
    state. This time, we'll just set work to be done, on the coffee maker and dishwasher. Lastly, we'll call the doKitchenWork
    method.

                        kitchen.setKitchenState(true,false,true);
                        kitchen.doKitchenWork();

    And if we run that code,

                        Brewing Coffee
                        Washing Dishes

    because we only said there was work for the coffee maker and dishwasher, we see that those appliances do their bit of
    work. You can see the output, Brewing Coffee, and Washing Dishes. If our smart kitchen added or removed appliances, or
    their types changed, the method doKitchenWork could still be called the same way, from the calling code. This means
    that SmartKitchen can manage all it's appliances and their inner workings, simplifying what the calling code needs to
    know, and do. Hopefully, you can see that composition is very very powerful.
*/


public class Main {
    public static void main(String[] args) {

        SmartKitchen kitchen = new SmartKitchen();

//        kitchen.getDishWasher().setHasWorkToDo(true);
//        kitchen.getIceBox().setHasWorkToDo(true);
//        kitchen.getBrewMaster().setHasWorkToDo(true);
//
//        kitchen.getDishWasher().doDishes();
//        kitchen.getIceBox().orderFood();
//        kitchen.getBrewMaster().brewCoffee();

        kitchen.setKitchenState(true,false,true);
        kitchen.doKitchenWork();
    }
}
