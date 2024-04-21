# [File Tree Walking Challenge]()
<div align="justify">

In the _main_ method, I'll change the path back to the current working
directory, by changing the two dots to one.

```java  
public class Challenge {

    public static void main(String[] args) {

        //Path startingPath = Path.of("..");
        //Path startingPath = Path.of(".");
        Path startingPath = Path.of("./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/");
        //FileVisitor<Path> statsVisitor = new StatsVisitor(1);
        FileVisitor<Path> statsVisitor = new StatsVisitor(Integer.MAX_VALUE);
        try {
            Files.walkFileTree(startingPath, statsVisitor);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
```

I'll change my depth to include all directories.
That means I'll change the argument in the constructor
of the **StatsVisitor** instance, from one to _Integer.MAX_VALUE_.
Everything else here can stay the same in the _main_ method.
Next, I'm going to look at the nested StatsVisitor class.

```java  
private static class StatsVisitor extends SimpleFileVisitor<Path> {

    private Path initialPath = null;
    //private final Map<Path, Long> folderSizes = new LinkedHashMap<>();
    private final Map<Path, Map<String, Long>> folderSizes = new LinkedHashMap<>();
    private int initialCount;

    private int printLevel;

    private static final String DIR_CNT = "DirCount";
    private static final String FILE_SIZE = "fileSize";
    private static final String FILE_CNT = "fileCount";

    public StatsVisitor(int printLevel) {
        this.printLevel = printLevel;
    }

    
}
```

I'll be changing the type of the field, the **map**, _folderSizes_.
I'll change **Long** to be a map instead, keyed by a **String**,
and the value will be long in this nested map.
This map is going to hold my file size summary in bytes, 
my count of all files, and my count of all folders.
Making this change, gives me compiler errors,
everywhere this code is referencing that map.
Before I address those issues, I'll set up a couple of constants.
These will be the keys into this map.
I'll make all of these private, static, final, string. 
The first will be the key for the count of directories or subfolders.
File size is the accumulated file size,
so the sum of all files in the folders.
File count is the number of files in the folders.

```java  
@Override
public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
        throws IOException {

    Objects.requireNonNull(dir);
    Objects.requireNonNull(attrs);

    if (initialPath == null) {
        initialPath = dir;
        initialCount = dir.getNameCount();
    } else {
        int relativeLevel = dir.getNameCount() - initialCount;
        if (relativeLevel == 1) {
            folderSizes.clear();
        }
        //folderSizes.put(dir, 0L);
        folderSizes.put(dir, new HashMap<>());
    }
    return FileVisitResult.CONTINUE;
}
```

First, I'll go to the _preVisitDirectory_ method,
since this is the first point of contact, into the walk.
You'll remember, I used this method 
to create a map entry for every single directory.
I'm going to do something similar here,
but instead of putting `0L` as the value,
I'll change that to a new **HashMap** instance.
Fortunately, the types can be inferred,
so I can just put angle brackets there.
Next, I'll navigate up to the _visitFile_ directory method.

```java  
@Override
public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
        throws IOException {

    Objects.requireNonNull(file);
    Objects.requireNonNull(attrs);
    
    //folderSizes.merge(file.getParent(), 0L, (o, n) -> o += attrs.size());

    var parentMap = folderSizes.get(file.getParent());   // Map<String, Long>
    if (parentMap != null) {
        long fileSize = attrs.size();
        parentMap.merge(FILE_SIZE, fileSize, (o, n) -> o += n);
        parentMap.merge(FILE_CNT, 1L, Math::addExact);
    }
    return FileVisitResult.CONTINUE;
}
```

I'll remove the `folderSizes.merge` statement.
I'll replace that with the code to populate the nested map instead.
I'll start out by getting the parent folder's map for the current file's parent.
It's possible that the current file's parent might be the original path, 
and that's not part of my map, so I'll check for **null** here.
Remember, I'm purposely clearing the map at the start of each subfolder 
of the original path, to give feedback to the user.
It also doesn't chew up all the memory 
if I'm exercising this code on a large directory.
File attributes are available as a method argument, 
and I can get size from there.
I'll merge the file size. 
If it's there, I'll just put the file size in there. 
If this record isn't new, I need to add the file size 
to the current data, and since that will be the new value,
I can just use _n_ in my expression. 
For the file count, I'll do something similar, 
setting the value to _1L_ if the record didn't exist, 
or adding this value to the value in the map.
This time, I'll use a method reference, 
with a method on the **Math** class, so you can see an alternative.
That's all I need to do when I'm visiting a file.
For good measure, I'll override the last method left 
that I haven't implemented, on _SimpleFileVisitor_.
I'll insert this after the _visitFile_ method.

```java  
@Override
public FileVisitResult visitFileFailed(Path file, IOException exc)
        throws IOException {

    //return super.visitFileFailed(file, exc);
    Objects.requireNonNull(file);
    
    if (exc != null) {
        System.out.println(exc.getClass().getSimpleName() + " " + file);
    }
    return FileVisitResult.CONTINUE;
}
```

Using IntelliJ tools, I'll pick _visitFileFailed_.
As I did before, I'll click on the `super.visitFileFailed` there,
to bring up the code in the **parent** class.
I'll just copy the first statement here, 
and paste it in my method, instead of the _return_ statement.
This method has the path, and an _IOException_ as the argument.
I'll check this second argument to see if it's not **null**.
If not, I'll print the class name of the exception, 
as well as the file name of the problem file.
I'm printing the class name
because many of the exceptions are named, 
like _AccessDeniedException_, 
and _FileSystemLoopException_, for example, 
and offer enough information without a full stack trace.
Finally, I don't want to stop the walk, so I'll continue.
Ok, so one thing I want to point out at this point is that 
my class doesn't really need to be 
a subclass of the **SimpleFileVisitor** class anymore.
I've implemented custom implementations for every method, 
so it doesn't really make sense to have **SimpleFileVisitor** as a **parent**.

```java  
//private static class StatsVisitor extends SimpleFileVisitor<Path> {
private static class StatsVisitor implements FileVisitor<Path> {

}
```

I'll change my class declaration, 
and instead of _extends_ **SimpleFileVisitor**, 
I'll change that to _implements_ **FileVisitor**, 
with **Path** as the type.
Ok, I need to change one more method,
and I can test this thing out.
The _postVisitDirectory_ method is the one that's going to 
roll the data up to the parent levels.

```java  
@Override
public FileVisitResult postVisitDirectory(Path dir, IOException exc)
        throws IOException {

    Objects.requireNonNull(dir);

    if (dir.equals(initialPath)) {
        return FileVisitResult.TERMINATE;
    }

    int relativeLevel = dir.getNameCount() - initialCount;
    if (relativeLevel == 1) {
        folderSizes.forEach((key, value) -> {

            int level = key.getNameCount() - initialCount - 1;
            if (level < printLevel) {
                System.out.printf("%s[%s] - %,d bytes %n", "\t".repeat(level), key.getFileName(), value);
            }
        });

    } else {
        
        //long folderSize = folderSize.get(dir);
        //folderSize.merge(dir.getParent(), 0L, (o, n) -> o += folderSize);
        
        var parentMap = folderSizes.get(dir.getParent());
        var childMap = folderSizes.get(dir);
        long folderCount = childMap.getOrDefault(DIR_CNT, 0L);
        long fileSize = childMap.getOrDefault(FILE_SIZE, 0L);
        long fileCount = childMap.getOrDefault(FILE_CNT, 0L);

        parentMap.merge(DIR_CNT, folderCount + 1, (o, n) -> o += n);
        parentMap.merge(FILE_SIZE, fileSize, Math::addExact);
        parentMap.merge(FILE_CNT, fileCount, Math::addExact);
    }
    return FileVisitResult.CONTINUE;
}
```

I'll start by looking at the _else_ clause of this code.
I'll remove the two statements that are here.
First, I'll get the parent's map data again,
from the _folderSizes_ map, using `dir.getParent()`.
Next, I'll get the child's map.
I'll set up some local variables, 
for the three fields I'll be adding to the parent's summary fields.
Here, I'll get the directory counts from the map.
I need to use the _getOrDefault_ method, 
because this data may not exist in the map, 
in the case where a directory didn't have a subfolder in it, 
so I'll return `0L` in that case.
I'll do the same for file size.
That's true for file count too.
Next, I'll merge this data into the parent's mapped values.
For folder count, I'll include an extra increment by 1,
so that the current directory is added to the parent's summary.
For the other two fields,
I'll just insert them if these entries don't exist,
or add it to the existing value.
Ok, so that is it for the _else_ clause.
Next, I want to change how this data gets printed out,
so I'll move up to the _if-clause_.

```java  
@Override
public FileVisitResult postVisitDirectory(Path dir, IOException exc)
        throws IOException {

    Objects.requireNonNull(dir);

    if (dir.equals(initialPath)) {
        return FileVisitResult.TERMINATE;
    }

    int relativeLevel = dir.getNameCount() - initialCount;
    if (relativeLevel == 1) {
        folderSizes.forEach((key, value) -> {

            int level = key.getNameCount() - initialCount - 1;
            if (level < printLevel) {
                //System.out.printf("%s[%s] - %,d bytes %n", "\t".repeat(level), key.getFileName(), value);
                long size = value.getOrDefault(FILE_SIZE, 0L);
                System.out.printf("%s[%s] - %,d bytes, %d files, %d folders %n",
                        "\t".repeat(level), key.getFileName(), size,
                        value.getOrDefault(FILE_CNT, 0L),
                        value.getOrDefault(DIR_CNT, 0L));
            }
        });

    } else {
        var parentMap = folderSizes.get(dir.getParent());
        var childMap = folderSizes.get(dir);
        long folderCount = childMap.getOrDefault(DIR_CNT, 0L);
        long fileSize = childMap.getOrDefault(FILE_SIZE, 0L);
        long fileCount = childMap.getOrDefault(FILE_CNT, 0L);

        parentMap.merge(DIR_CNT, folderCount + 1, (o, n) -> o += n);
        parentMap.merge(FILE_SIZE, fileSize, Math::addExact);
        parentMap.merge(FILE_CNT, fileCount, Math::addExact);
    }
    return FileVisitResult.CONTINUE;
}
```

I'll remove the `System.out.printf` statement.
I'll do something similar, but first I want to set up a local variable for the size.
I can get the size from entry being iterated on.
If the file size isn't in the nested map, I'll return `0L` as the size.
This print statement will start like the previous one, 
with an indent level, the path name, and the number of bytes, 
but I also want the number of files and folders. 
Tabs will be based on the level, 
and I'll just print the last part of the path name, and then size. 
Next I'll get the file count, again using _getOrDefault_. 
And the same thing for dir count, or the count of subfolders.
Ok, that's it.
The code compiles and I should be able to run this for the current directory.

```html  
[Section_01_ExpressionsStatementsMore] - 2,025,110 bytes, 14 files, 1 folders 
	[Images] - 1,958,338 bytes, 3 files, 0 folders 
[Section_02_ControlFlow] - 62,523 bytes, 15 files, 0 folders 
[Section_03_OOP1] - 8,296,747 bytes, 56 files, 22 folders 
	[Course01_AccessModifiers] - 2,213 bytes, 1 files, 0 folders 
	[Course02_ClassesUsingGetterMethods] - 11,325 bytes, 3 files, 0 folders 
	[Course03_SetterMethodsObjects] - 2,111 bytes, 2 files, 0 folders 
	[Course04_ClassesChallenge] - 2,690 bytes, 2 files, 0 folders 
	[Course05_Constructors1] - 3,340 bytes, 2 files, 0 folders 
	[Course06_Constructor2] - 3,213 bytes, 2 files, 0 folders 
	[Course07_ConstructorChallenge] - 2,256 bytes, 2 files, 0 folders 
	[Course08_ReferencesObjects] - 1,242 bytes, 2 files, 0 folders 
	[Course09_StaticInstanceVariables] - 184 bytes, 1 files, 0 folders 
	[Course10_StaticInstanceMethods] - 184 bytes, 1 files, 0 folders 
	[Course11_POJOAndRecord] - 3,016 bytes, 3 files, 0 folders 
	[Course12_POJOAndOverriddenMethods] - 2,309 bytes, 3 files, 0 folders 
	[Course13_Inheritance1] - 1,590 bytes, 3 files, 0 folders 
	[Course14_Inheritance2] - 2,663 bytes, 3 files, 0 folders 
	[Course15_Inheritance3] - 4,365 bytes, 4 files, 0 folders 
	[Course16_JavaLangObject] - 1,252 bytes, 1 files, 0 folders 
	[Course17_InheritanceChallenge] - 3,950 bytes, 5 files, 0 folders 
	[Course18_TextBlockAndFormatting] - 1,193 bytes, 1 files, 0 folders 
	[Course19_StringClassMethods1] - 2,147 bytes, 1 files, 0 folders 
	[Course20_StringClassMethods2] - 1,669 bytes, 1 files, 0 folders 
	[Course21_StringBuilderClass] - 1,623 bytes, 1 files, 0 folders 
	[images] - 8,039,020 bytes, 11 files, 0 folders 
[Section_04_OOP2] - 1,104,423 bytes, 50 files, 13 folders 
	[Course01_Composition1] - 3,574 bytes, 3 files, 0 folders 
	[Course02_Composition2] - 3,818 bytes, 3 files, 0 folders 
	[Course03_CompositionChallenge] - 10,245 bytes, 3 files, 0 folders 
	[Course04_Encapsulation] - 2,626 bytes, 3 files, 0 folders 
	[Course05_EncapsulationChallenge] - 1,566 bytes, 2 files, 0 folders 
	[Course06_Polymorhism] - 2,747 bytes, 2 files, 0 folders 
	[Course07_CastingClassesVarReference] - 3,230 bytes, 2 files, 0 folders 
	[Course08_InstanceOfOperator] - 3,237 bytes, 2 files, 0 folders 
	[Course09_PolymorphismChallenge] - 16,002 bytes, 3 files, 0 folders 
	[Course10_OOPMasterChallenge] - 44,965 bytes, 8 files, 1 folders 
		[MyAnswer] - 9,060 bytes, 2 files, 0 folders 
	[Course11_OrganizingPackagesImport] - 187 bytes, 1 files, 0 folders 
	[images] - 919,409 bytes, 17 files, 0 folders 
[Section_05_Arrays] - 69,925 bytes, 10 files, 8 folders 
	[Course01_Arrays] - 1,736 bytes, 1 files, 0 folders 
	[Course02_JavaUtilArraysSortFillCopyOf] - 3,088 bytes, 2 files, 0 folders 
	[Course03_ReferenceAndValueTypes] - 709 bytes, 1 files, 0 folders 
	[Course04_VariableArgumentsVarargs] - 778 bytes, 1 files, 0 folders 
	[Course05_MinElementChallenge] - 1,258 bytes, 1 files, 0 folders 
	[Course06_ReverseArrayChallenge] - 688 bytes, 1 files, 0 folders 
	[Course07_2DArrays] - 1,086 bytes, 1 files, 0 folders 
	[Course08_MultiDimensionalArrays] - 1,226 bytes, 1 files, 0 folders 
[Section_06_ArrayList_LinkedList_Iterators_Autoboxing] - 609,954 bytes, 25 files, 10 folders 
	[Course01_CollectionsArrayLists] - 4,596 bytes, 2 files, 0 folders 
	[Course02_ArrayListChallenge] - 12,304 bytes, 2 files, 0 folders 
	[Course03_BigONotation] - 210 bytes, 1 files, 0 folders 
	[Course04_LinkedList] - 4,703 bytes, 1 files, 0 folders 
	[Course05_Iterators] - 1,853 bytes, 1 files, 0 folders 
	[Course06_LinkedListChallenge] - 27,232 bytes, 3 files, 0 folders 
	[Course07_AutoboxingUnboxing] - 1,861 bytes, 1 files, 0 folders 
	[Course08_AutoboxingUnboxing_Challenge] - 17,580 bytes, 3 files, 0 folders 
	[Course09_EnumType] - 1,397 bytes, 2 files, 0 folders 
	[images] - 424,940 bytes, 8 files, 0 folders 
[Section_07_Abstraction_Interface] - 459,992 bytes, 37 files, 7 folders 
	[Course01_Abstract] - 4,554 bytes, 5 files, 0 folders 
	[Course02_Abstract_Challenge] - 18,079 bytes, 5 files, 0 folders 
	[Course03_Interfaces] - 5,253 bytes, 5 files, 0 folders 
	[Course04_default_MethodsOn_Interfaces] - 7,055 bytes, 6 files, 0 folders 
	[Course05_PublicStaticMethodsOnInterface] - 5,739 bytes, 5 files, 0 folders 
	[Course06_Interface_Challenge] - 20,325 bytes, 5 files, 0 folders 
	[images] - 295,293 bytes, 5 files, 0 folders 
[Section_08_Generics] - 510,221 bytes, 42 files, 17 folders 
	[Course01_Generics_Part1] - 2,399 bytes, 2 files, 0 folders 
	[Course02_Generics_Part2] - 5,068 bytes, 3 files, 0 folders 
	[Course03_Generics_Part3] - 8,255 bytes, 4 files, 0 folders 
	[Course04_Generics_Challenge] - 17,727 bytes, 6 files, 0 folders 
	[Course05_RevisitingComparable] - 1,782 bytes, 1 files, 0 folders 
	[Course06_ComparableVsComparator] - 3,346 bytes, 1 files, 0 folders 
	[Course07_GenericsExtra] - 3,931 bytes, 3 files, 1 folders 
		[model] - 1,452 bytes, 2 files, 0 folders 
	[Course08_GenericMethodsWildcards] - 3,873 bytes, 3 files, 1 folders 
		[model] - 1,470 bytes, 2 files, 0 folders 
	[Course09_StaticMethodsMultipleUpperBounds] - 5,696 bytes, 5 files, 2 folders 
		[model] - 2,084 bytes, 2 files, 0 folders 
		[util] - 1,340 bytes, 2 files, 0 folders 
	[Course10_StudentChallenge] - 19,843 bytes, 7 files, 2 folders 
		[model] - 2,977 bytes, 3 files, 0 folders 
		[util] - 1,046 bytes, 2 files, 0 folders 
	[images] - 318,800 bytes, 6 files, 0 folders 
[Section_09_NestedClassesAndTypes] - 129,617 bytes, 25 files, 12 folders 
	[Course01_StaticNestedClasses] - 3,037 bytes, 3 files, 1 folders 
		[domain] - 1,906 bytes, 2 files, 0 folders 
	[Course02_InnerClasses] - 4,862 bytes, 4 files, 1 folders 
		[domain] - 2,936 bytes, 3 files, 0 folders 
	[Course03_InnerClassBillsBurgerChallengeRecover] - 2,450 bytes, 2 files, 0 folders 
	[Course04_InnerClassChallenge] - 22,300 bytes, 3 files, 0 folders 
	[Course05_LocalClasses] - 5,865 bytes, 4 files, 1 folders 
		[domain] - 2,620 bytes, 3 files, 0 folders 
	[Course06_AnonymousClasses] - 7,916 bytes, 5 files, 1 folders 
		[domain] - 2,632 bytes, 3 files, 0 folders 
	[Course07_LocalAnonymousClassChallenge] - 10,944 bytes, 3 files, 1 folders 
		[domain] - 207 bytes, 1 files, 0 folders 
[Section_10_LambdaExpressionsAndFunctionalInterfaces] - 198,795 bytes, 16 files, 11 folders 
	[Course01_LambdaExpressions_Part1] - 2,310 bytes, 1 files, 0 folders 
	[Course02_LambdaExpressions_Part2] - 1,237 bytes, 1 files, 0 folders 
	[Course03_LambdaExpressions_Part3] - 2,098 bytes, 2 files, 0 folders 
	[Course04_FunctionalInterfacesConsumerPredicate] - 2,604 bytes, 1 files, 0 folders 
	[Course05_FunctionAndSupplierInterfaces] - 4,141 bytes, 1 files, 0 folders 
	[Course06_LambdaMiniChallenges] - 18,558 bytes, 2 files, 0 folders 
	[Course07_LambdaExpressionChallenge] - 14,578 bytes, 2 files, 0 folders 
	[Course08_MethodReferences_Part1] - 1,701 bytes, 1 files, 0 folders 
	[Course09_MethodReferences_Part2] - 2,678 bytes, 1 files, 0 folders 
	[Course10_MethodReferenceChallenge] - 16,182 bytes, 2 files, 0 folders 
	[Course11_ChainingLambdas] - 3,645 bytes, 1 files, 0 folders 
[Section_11_Collections] - 3,165,931 bytes, 126 files, 34 folders 
	[Course01_CollectionsFramework] - 975 bytes, 1 files, 0 folders 
	[Course02_CollectionMethodsPart1] - 2,666 bytes, 2 files, 0 folders 
	[Course03_CollectionMethodsPart2] - 3,945 bytes, 2 files, 0 folders 
	[Course04_CollectionMethodsPart3] - 5,146 bytes, 2 files, 0 folders 
	[Course05_CollectionMethodsPart4] - 7,164 bytes, 2 files, 0 folders 
	[Course06_CollectionsChallenge_CardGamePart1] - 43,060 bytes, 6 files, 2 folders 
		[games] - 3,493 bytes, 4 files, 1 folders 
			[poker] - 3,037 bytes, 3 files, 0 folders 
	[Course07_CollectionsChallenge_CardGamePart2] - 45,616 bytes, 6 files, 2 folders 
		[games] - 6,037 bytes, 4 files, 1 folders 
			[poker] - 5,581 bytes, 3 files, 0 folders 
	[Course08_HashCodes_Sets_Maps] - 3,084 bytes, 2 files, 0 folders 
	[Course09_CodeSetupForSetsMapsPart1] - 3,955 bytes, 3 files, 0 folders 
	[Course10_CodeSetupForSetsMapsPart2] - 5,688 bytes, 3 files, 0 folders 
	[Course11_SetOperations_SymmetricAsymmetricResults] - 6,968 bytes, 3 files, 0 folders 
	[Course12_TaskAndTaskDataCodeSetup] - 7,807 bytes, 4 files, 0 folders 
	[Course13_SetOperationsChallenge] - 27,648 bytes, 4 files, 0 folders 
	[Course14_LinkedHashSetAndTreeSet] - 8,943 bytes, 4 files, 0 folders 
	[Course15_TreeSetMethods] - 11,019 bytes, 4 files, 0 folders 
	[Course16_TreeSetChallenge] - 50,446 bytes, 3 files, 0 folders 
	[Course17_TreeSetChallengeBonus] - 6,729 bytes, 2 files, 0 folders 
	[Course18_MapInterfaceAndFunctionality] - 14,152 bytes, 5 files, 0 folders 
	[Course19_MapFunctionalityMore] - 16,516 bytes, 5 files, 0 folders 
	[Course20_MapsViewCollections] - 19,626 bytes, 6 files, 0 folders 
	[Course21_HashMapChallengePart1] - 27,254 bytes, 3 files, 0 folders 
	[Course22_HashMapChallengePart2] - 4,789 bytes, 2 files, 0 folders 
	[Course23_LinkedHashMapAndTreeMap] - 3,950 bytes, 2 files, 0 folders 
	[Course24_WorkingWithTreeMapMethods] - 6,694 bytes, 2 files, 0 folders 
	[Course25_TargetedCollectionsForEnumTypes] - 1,708 bytes, 1 files, 0 folders 
	[Course26_CollectionsFrameworkFinalChallenge] - 74,357 bytes, 17 files, 3 folders 
		[Part1] - 4,917 bytes, 5 files, 0 folders 
		[Part2] - 9,697 bytes, 6 files, 0 folders 
		[Part3] - 10,874 bytes, 5 files, 0 folders 
	[images] - 2,427,311 bytes, 29 files, 0 folders 
[Section_12_Immutable_Unmodifable_Classes] - 2,724,102 bytes, 141 files, 49 folders 
	[Course01_TheFinalModifier] - 3,003 bytes, 4 files, 3 folders 
		[consumer] - 787 bytes, 2 files, 1 folders 
			[specific] - 783 bytes, 1 files, 0 folders 
		[generic] - 1,059 bytes, 1 files, 0 folders 
	[Course02_AppliedFinalModifiers] - 4,155 bytes, 4 files, 3 folders 
		[consumer] - 1,150 bytes, 2 files, 1 folders 
			[specific] - 1,013 bytes, 1 files, 0 folders 
		[generic] - 1,058 bytes, 1 files, 0 folders 
	[Course03_WhenChangeIsGood] - 7,397 bytes, 5 files, 5 folders 
		[consumer] - 995 bytes, 1 files, 1 folders 
			[specific] - 995 bytes, 1 files, 0 folders 
		[external] - 477 bytes, 1 files, 1 folders 
			[util] - 477 bytes, 1 files, 0 folders 
		[generic] - 1,053 bytes, 1 files, 0 folders 
	[Course04_ImmutableClasses] - 9,129 bytes, 8 files, 3 folders 
		[external] - 2,816 bytes, 2 files, 1 folders 
			[domain] - 917 bytes, 1 files, 0 folders 
		[hacker] - 518 bytes, 1 files, 0 folders 
	[Course05_ImmutableClassesChallenge] - 13,223 bytes, 4 files, 1 folders 
		[bank] - 1,981 bytes, 2 files, 0 folders 
	[Course06_DefensiveShallowDeepCopies] - 6,910 bytes, 1 files, 0 folders 
	[Course07_UnmodifiableCollections] - 3,499 bytes, 2 files, 0 folders 
	[Course08_UnmodifiableCollectionsChallenge] - 33,941 bytes, 6 files, 2 folders 
		[bank] - 4,818 bytes, 3 files, 0 folders 
		[dto] - 1,416 bytes, 1 files, 0 folders 
	[Course09_ConstructorReviews] - 11,827 bytes, 12 files, 6 folders 
		[Rev1_IntroductionToInitializer] - 3,049 bytes, 3 files, 1 folders 
			[external] - 1,360 bytes, 1 files, 0 folders 
		[Rev2_RecordConstructor] - 3,899 bytes, 4 files, 1 folders 
			[external] - 1,344 bytes, 1 files, 0 folders 
		[Rev3_EnumConstructor] - 4,879 bytes, 5 files, 1 folders 
			[external] - 1,342 bytes, 1 files, 0 folders 
	[Course10_GameConsoleChallenge] - 6,306 bytes, 7 files, 1 folders 
		[game] - 5,644 bytes, 6 files, 0 folders 
	[Course11_PirateGameChallenge] - 43,414 bytes, 11 files, 2 folders 
		[game] - 5,696 bytes, 6 files, 0 folders 
		[pirate] - 7,179 bytes, 3 files, 0 folders 
	[Course12_FinalClasses] - 15,202 bytes, 11 files, 2 folders 
		[game] - 5,660 bytes, 6 files, 0 folders 
		[pirate] - 7,145 bytes, 3 files, 0 folders 
	[Course13_SealedClasses] - 18,665 bytes, 20 files, 3 folders 
		[game] - 7,749 bytes, 7 files, 0 folders 
		[pirate] - 7,151 bytes, 3 files, 0 folders 
		[sealed] - 2,007 bytes, 8 files, 0 folders 
	[Course14_FinalChallengeOfSection] - 60,998 bytes, 26 files, 3 folders 
		[game] - 5,732 bytes, 6 files, 0 folders 
		[pirate] - 14,320 bytes, 9 files, 0 folders 
		[sealed] - 2,094 bytes, 8 files, 0 folders 
	[images] - 2,256,753 bytes, 19 files, 0 folders 
[Section_13_Streams] - 1,638,738 bytes, 91 files, 18 folders 
	[Course01_StreamInAction] - 2,041 bytes, 1 files, 0 folders 
	[Course02_StreamPipeline] - 2,492 bytes, 1 files, 0 folders 
	[Course03_StreamSources] - 4,608 bytes, 1 files, 0 folders 
	[Course04_StreamSourcesChallenge] - 14,051 bytes, 2 files, 0 folders 
	[Course05_IntermediateOperationsPart1] - 2,888 bytes, 1 files, 0 folders 
	[Course06_IntermediateOperationsPart2] - 2,550 bytes, 2 files, 0 folders 
	[Course07_TerminalOperations] - 2,052 bytes, 2 files, 0 folders 
	[Course08_StreamingStudentsProject] - 34,735 bytes, 5 files, 0 folders 
	[Course09_TerminalOperationsChallenge1] - 30,600 bytes, 5 files, 0 folders 
	[Course10_CollectorAndCollectorsClass] - 11,898 bytes, 4 files, 0 folders 
	[Course11_CollectReduceTerminalOperations] - 14,299 bytes, 5 files, 0 folders 
	[Course12_TerminalOperationsChallenge2] - 39,733 bytes, 7 files, 0 folders 
	[Course13_WhatsOptional] - 18,509 bytes, 7 files, 0 folders 
	[Course14_TerminalOptionalOperations] - 21,791 bytes, 8 files, 0 folders 
	[Course15_StreamsToMaps] - 24,150 bytes, 9 files, 0 folders 
	[Course16_MapsToStreams] - 29,185 bytes, 10 files, 0 folders 
	[Course17_StreamsChallenge] - 47,390 bytes, 11 files, 0 folders 
	[images] - 1,126,738 bytes, 9 files, 0 folders 
[Section_16_InputOutputFiles] - 7,253,339 bytes, 126 files, 44 folders 
	[Course01_ExceptionHandling] - 5,189 bytes, 2 files, 2 folders 
		[ChecedUncheckedFinally] - 1,192 bytes, 1 files, 0 folders 
		[TryWithResources] - 3,997 bytes, 1 files, 0 folders 
	[Course02_UsingFilesAndPaths] - 9,381 bytes, 3 files, 3 folders 
		[Part1] - 6,381 bytes, 2 files, 1 folders 
			[files] - 0 bytes, 1 files, 0 folders 
		[Part2] - 3,000 bytes, 1 files, 0 folders 
	[Course03_MethodsOnPath] - 2,476 bytes, 1 files, 0 folders 
	[Course04_FilesClass] - 8,216 bytes, 2 files, 2 folders 
		[DirectoryListings] - 5,000 bytes, 1 files, 0 folders 
		[FileTree] - 3,216 bytes, 1 files, 0 folders 
	[Course05_FileTreeWalkingChallenge] - 26,602 bytes, 4 files, 0 folders 
	[Course06_ReadingTextFromInputFiles] - 25,501 bytes, 10 files, 3 folders 
		[Part1_ReadingFiles] - 8,896 bytes, 4 files, 0 folders 
		[Part2_ScannerProject] - 7,121 bytes, 4 files, 0 folders 
		[Part3_ReadingWithNIO2] - 9,484 bytes, 2 files, 0 folders 
	[Course07_ReadingFileChallenge] - 140,871 bytes, 4 files, 0 folders 
	[Course08_WritingDataToFiles] - 57,298 bytes, 22 files, 4 folders 
		[Part1] - 21,369 bytes, 9 files, 1 folders 
			[student] - 9,546 bytes, 5 files, 0 folders 
		[Part2] - 31,307 bytes, 12 files, 1 folders 
			[student] - 9,546 bytes, 5 files, 0 folders 
	[Course09_WritingFilesChallenge] - 50,200 bytes, 14 files, 1 folders 
		[student] - 18,603 bytes, 5 files, 0 folders 
	[Course10_ManagingFiles] - 3,178,099 bytes, 22 files, 10 folders 
		[Part1] - 640,460 bytes, 5 files, 2 folders 
			[files] - 627,118 bytes, 3 files, 1 folders 
				[data] - 258 bytes, 1 files, 0 folders 
		[Part2] - 2,530,010 bytes, 14 files, 6 folders 
			[files] - 1,254,628 bytes, 6 files, 2 folders 
				[data] - 947 bytes, 3 files, 1 folders 
					[newdata] - 269 bytes, 1 files, 0 folders 
			[resources] - 1,254,668 bytes, 6 files, 2 folders 
				[data] - 963 bytes, 3 files, 1 folders 
					[newdata] - 273 bytes, 1 files, 0 folders 
	[Course11_FileManipulationChallenge] - 13,617 bytes, 5 files, 3 folders 
		[public] - 1,442 bytes, 3 files, 2 folders 
			[assets] - 760 bytes, 2 files, 1 folders 
				[icons] - 276 bytes, 1 files, 0 folders 
	[Course12_RandomAccessFile] - 1,944,940 bytes, 8 files, 0 folders 
	[Course13_RandomAccessFileChallenge] - 11,910 bytes, 2 files, 0 folders 
	[Course14_DataStreamsAndSerialization] - 16,513 bytes, 3 files, 0 folders 
	[Course15_SerializationAndChange] - 31,251 bytes, 3 files, 0 folders 
	[images] - 1,524,935 bytes, 20 files, 0 folders 
[Section_19_Concurrency] - 566,808 bytes, 54 files, 29 folders 
	[Course01_BasicsOfJavaThreads] - 19,672 bytes, 2 files, 0 folders 
	[Course02_ThreadCreationExecution] - 16,235 bytes, 2 files, 0 folders 
	[Course03_InteractingRunningThread] - 24,702 bytes, 1 files, 0 folders 
	[Course04_ThreadChallenge] - 7,212 bytes, 1 files, 0 folders 
	[Course05_MultithreadingAndMemory] - 16,741 bytes, 2 files, 0 folders 
	[Course06_ConcurrentThreadsConcepts] - 19,513 bytes, 3 files, 0 folders 
	[Course07_Synchronization] - 35,485 bytes, 4 files, 2 folders 
		[Part1_SynchronizedMethods] - 17,251 bytes, 2 files, 0 folders 
		[Part2_synchronizedBlocks] - 18,234 bytes, 2 files, 0 folders 
	[Course08_ProducerConsumerApp] - 14,280 bytes, 1 files, 0 folders 
	[Course09_DeadlocksWaitNotify] - 10,639 bytes, 1 files, 0 folders 
	[Course10_SynchronizationChallenge] - 11,369 bytes, 2 files, 0 folders 
	[Course11_ConcurrentLocksClass] - 24,501 bytes, 1 files, 0 folders 
	[Course12_ExecutorService] - 26,342 bytes, 2 files, 0 folders 
	[Course13_FixedThreadPool] - 19,729 bytes, 2 files, 0 folders 
	[Course14_AdditionalThreadPools] - 29,830 bytes, 2 files, 0 folders 
	[Course15_ExecuterServiceChallenge] - 17,460 bytes, 2 files, 0 folders 
	[Course16_SchedulingTasks] - 20,217 bytes, 1 files, 0 folders 
	[Course17_WorkStealingPool] - 21,473 bytes, 1 files, 0 folders 
	[Course18_ParallelStreams] - 11,748 bytes, 1 files, 0 folders 
	[Course19_OrderingReducingCollecting] - 22,527 bytes, 1 files, 0 folders 
	[Course20_SynchronizedConcurrentCollections] - 19,659 bytes, 2 files, 0 folders 
	[Course21_ThreadSafeListQueues] - 29,280 bytes, 4 files, 0 folders 
	[Course22_ArrayBlockingQueueConsumerTasks] - 27,204 bytes, 3 files, 0 folders 
	[Course23_RevisitingDeadlockCommonProblems] - 20,354 bytes, 1 files, 0 folders 
	[Course24_LivelockExample] - 43,051 bytes, 3 files, 0 folders 
	[Course25_StarvationFairLocks] - 17,537 bytes, 4 files, 0 folders 
	[Course26_MoreConcurrencyFeatures] - 20,152 bytes, 2 files, 0 folders 
	[Course27_WatcherServiceFileWatcher] - 19,896 bytes, 3 files, 0 folders 
```

Here, I see all my FileWalker project's folders listed,
and the size, number of files and number of folders I have in each.
You can try this code on much larger folders,
but I'd recommend changing the print level 
(the integer you pass to the stats visitor constructor) 
back to one or two.
It takes a little while to run this on _root_, for example, 
if you do have access to root.
You may get some access denied exceptions along the way.

One thing that makes the _walkFileTree_ method kind of nice is, 
you can catch the exception and ignore it and keep running, 
which is harder to do when you're working with streams.
It's also frowned on, to have side effects 
with functional programming, which streams are.
I'll show you one example, 
that doesn't propagate or roll up the data to the parent's level,
but simply aggregates it, at the level you choose.
I'll create a new class in the same package, 
with a _main_ method, and call it **ChallengeStreams**.

```java  
public class ChallengeStreams {

    public static void main(String[] args) {

        //Path startingPath = Path.of("..");
        Path startingPath = Path.of("./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/");
        int index = startingPath.getNameCount();
        try (var paths = Files.walk(startingPath, Integer.MAX_VALUE)) {  // paths : Stream<Path>
            paths.filter(Files::isRegularFile)                           // Stream<Path> 
                    .collect(Collectors.groupingBy(p -> p.subpath(index, index + 1),
                            Collectors.summarizingLong(
                                    p -> {
                                        try {
                                            return Files.size(p);
                                        } catch (IOException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                            )))                                          // Map<Path, LongSummaryStatistics>
                    .forEach((key, value) -> 
                    {System.out.printf("[%s] %,d bytes, %d files %n", key, value.getSum(), value.getCount());});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

I'll start in the _main_ method with a local variable, 
_startingPath_ and this time, set that to two dots.
I'll get the starting path's index count.
All my directories will be relative to this.
Next, I need a _try-with-resources_ statement,
and here I'll use `Files.walk` with the _startingPath_, 
and `Integer.MAX_VALUE` for depth.
I'll need a _catch_ clause for _IOExceptions_.
I'll just print the stack trace of the error out.
Now, I'll start my stream pipeline, starting with my source paths.
First I'll _filter_, and here I only want to look at files, not directories.
That's because, in this code, I'm only going to sum up the file size, 
and get the file counts with built-in features of the stream pipeline.
I'll use the collect terminal operation with _groupingBy_.
Here I want to group by the first relative path name,
so I'm going to use _subpath_ with the index of the original path's number of parts,
and I'll just add one to that, 
so this folder will just have a single part of the path name.
In other words, my data will be grouped by the first level of subfolders.
Next, I'll call _summarizingLong_, which gives me summary statistics on the field I want.
If I use file size, this data will give me both the sum, the count, 
and other stats like average, and so on.
My lambda expression will return the size, using `Files.size`.
Unfortunately, this throws an _IOException_,
so I need a try catch block around the call,
which is pretty ugly here. I'll return the size.
I can append a for Each to the result, a
map, with paths and LongSummaryStatistics.
I'll print this data out, much like
I did before, without indents though.
I'll print the sum on that result, and the count.
Ok, that's it.
I'll run that:

```html  
[Section_10_LambdaExpressionsAndFunctionalInterfaces] 198,795 bytes, 16 files 
[Section_01_ExpressionsStatementsMore] 2,025,110 bytes, 14 files 
[Section_16_InputOutputFiles] 7,275,340 bytes, 126 files 
[Section_12_Immutable_Unmodifable_Classes] 2,724,102 bytes, 141 files 
[Section_04_OOP2] 1,104,423 bytes, 50 files 
[Section_11_Collections] 3,165,931 bytes, 126 files 
[Section_05_Arrays] 69,925 bytes, 10 files 
[Section_08_Generics] 510,221 bytes, 42 files 
[Section_09_NestedClassesAndTypes] 129,617 bytes, 25 files 
[Section_02_ControlFlow] 62,523 bytes, 15 files 
[Section_13_Streams] 1,638,738 bytes, 91 files 
[Section_07_Abstraction_Interface] 459,992 bytes, 37 files 
[Section_06_ArrayList_LinkedList_Iterators_Autoboxing] 609,954 bytes, 25 files 
[Section_03_OOP1] 8,296,747 bytes, 56 files 
```

I'll get my intellij project folders printed out, with the size and number of files.
That was quick work.
Again, it's not rolling the data up at every single level, 
but only by the level I grouped by, and this code ignores subfolder counts.
If this is the data you care about, 
this is a pretty quick way to get that first level of information.
Notice these aren't ordered because the resulting map is a hash map.
I can very quickly change that by piping the map entries to a stream.

```java  
public class ChallengeStreams {

    public static void main(String[] args) {

        //Path startingPath = Path.of("..");
        Path startingPath = Path.of("./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/");
        int index = startingPath.getNameCount();
        try (var paths = Files.walk(startingPath, Integer.MAX_VALUE)) {  // paths : Stream<Path>
            paths.filter(Files::isRegularFile)                           // Stream<Path> 
                    .collect(Collectors.groupingBy(p -> p.subpath(index, index + 1),
                            Collectors.summarizingLong(
                                    p -> {
                                        try {
                                            return Files.size(p);
                                        } catch (IOException e) {
                                            throw new RuntimeException(e);
                                        }
                                    })))                                 // Map<Path, LongSummaryStatistics>
                    .entrySet()                                          // Set<Entry<...>>
                    .stream()                                            // Stream<Entry<...>>
                    .sorted(Comparator.comparing(e -> e.getKey().toString()))
                    .forEach(e -> 
                    {System.out.printf("[%s] %,d bytes, %d files %n", 
                            e.getKey(), e.getValue().getSum(), e.getValue().getCount());});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

I'll call entrySet on the first stream's resulting map.
I'll follow that with a call to a stream method. 
I'll sort, using `Comparator.comparing`, passing it the entry, and using
getKey (or the path) to sort on.
I have to change my _forEach_ now, 
and pass it the entry, rather than the key and value.
Running that:

```html  
[Section_01_ExpressionsStatementsMore] 2,025,110 bytes, 14 files
[Section_02_ControlFlow] 62,523 bytes, 15 files
[Section_03_OOP1] 8,296,747 bytes, 56 files
[Section_04_OOP2] 1,104,423 bytes, 50 files
[Section_05_Arrays] 69,925 bytes, 10 files
[Section_06_ArrayList_LinkedList_Iterators_Autoboxing] 609,954 bytes, 25 files
[Section_07_Abstraction_Interface] 459,992 bytes, 37 files
[Section_08_Generics] 510,221 bytes, 42 files
[Section_09_NestedClassesAndTypes] 129,617 bytes, 25 files
[Section_10_LambdaExpressionsAndFunctionalInterfaces] 198,795 bytes, 16 files
[Section_11_Collections] 3,165,931 bytes, 126 files
[Section_12_Immutable_Unmodifable_Classes] 2,724,102 bytes, 141 files
[Section_13_Streams] 1,638,738 bytes, 91 files
[Section_16_InputOutputFiles] 7,279,203 bytes, 126 files
```

I'll now get my folders printed in order.
I can clean this stream pipeline up a little bit, 
where this try catch surrounds the code to get the size.

```java  
public class ChallengeStreams {

    public static void main(String[] args) {

        //Path startingPath = Path.of("..");
        Path startingPath = Path.of("./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/");
        int index = startingPath.getNameCount();
        try (var paths = Files.walk(startingPath, Integer.MAX_VALUE)) {  // paths : Stream<Path>
            paths.filter(Files::isRegularFile)
                    .collect(Collectors.groupingBy(p -> p.subpath(index, index + 1),
                            
                            /*Collectors.summarizingLong(
                                    p -> {
                                        try {
                                            return Files.size(p);
                                        } catch (IOException e) {
                                            throw new RuntimeException(e);
                                        }
                                    })))                                 // Map<Path, LongSummaryStatistics>
                            */
                            
                            Collectors.summarizingLong(p -> p.toFile().length()))) // Map<Path, LongSummaryStatistics>
                    .entrySet()                                          // Set<Entry<...>>
                    .stream()                                            // Stream<Entry<...>>
                    .sorted(Comparator.comparing(e -> e.getKey().toString()))
                    .forEach(e -> {
                        System.out.printf("[%s] %,d bytes, %d files %n",
                                e.getKey(), e.getValue().getSum(),
                                e.getValue().getCount());
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

I'll remove all of this.
I'll replace that with a different lambda expression.
In this case, I'll use a method on path, called _toFile_, 
which will give me a **file** instance.
That has length as a field, and I can get that, which gives me the same data as size.
Using this method is a bit less performant, 
but if you're not doing anything too intense, 
this is one way to have slightly easier to read code.
You could also wrap the call to `Files.size` in a wrapper method, 
that catches the exception, and call that method here.
Running this code gives me the same result.
What's nice about this second stream in the pipeline is 
that I could continue to hone and filter these summary results.
For example, I'll filter out all folders that are at least one megabyte.

```java  
public class ChallengeStreams {

    public static void main(String[] args) {

        //Path startingPath = Path.of("..");
        Path startingPath = Path.of("./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/");
        int index = startingPath.getNameCount();
        try (var paths = Files.walk(startingPath, Integer.MAX_VALUE)) {  // paths : Stream<Path>
            paths.filter(Files::isRegularFile)
                    .collect(Collectors.groupingBy(p -> p.subpath(index, index + 1),
                            
                            /*Collectors.summarizingLong(
                                    p -> {
                                        try {
                                            return Files.size(p);
                                        } catch (IOException e) {
                                            throw new RuntimeException(e);
                                        }
                                    })))                                 // Map<Path, LongSummaryStatistics>
                            */
                            
                            Collectors.summarizingLong(p -> p.toFile().length()))) // Map<Path, LongSummaryStatistics>
                    .entrySet()                                          // Set<Entry<...>>
                    .stream()                                            // Stream<Entry<...>>
                    .sorted(Comparator.comparing(e -> e.getKey().toString()))
                    .filter(e -> e.getValue().getSum() > 1_000_000)
                    .forEach(e -> {
                        System.out.printf("[%s] %,d bytes, %d files %n",
                                e.getKey(), e.getValue().getSum(),
                                e.getValue().getCount());
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

I'll add a filter, and filter by the sum greater than one million bytes.
Running this code:

```html  
[Section_01_ExpressionsStatementsMore] 2,025,110 bytes, 14 files
[Section_03_OOP1] 8,296,747 bytes, 56 files
[Section_04_OOP2] 1,104,423 bytes, 50 files
[Section_11_Collections] 3,165,931 bytes, 126 files
[Section_12_Immutable_Unmodifable_Classes] 2,724,102 bytes, 141 files
[Section_13_Streams] 1,638,738 bytes, 91 files
[Section_16_InputOutputFiles] 7,283,497 bytes, 126 files 
```

I only have a couple of section folders that meet that criteria.
I could run this code with a filter on a billion bytes, 
to look for the largest folders in a directory, for example.
Depending on what you might be doing, 
one of the stream methods might easily give you what you need.
If you need to roll up the data in a hierarchical way,
the _walkFileTree_ is probably a better solution for that kind of thing.
Now that you're pretty adept at exploring a directory tree, 
it's time to move on, and focus on reading data from files.
</div>