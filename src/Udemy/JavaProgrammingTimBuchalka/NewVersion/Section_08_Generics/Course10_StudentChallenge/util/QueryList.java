package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_08_Generics.Course10_StudentChallenge.util;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_08_Generics.Course10_StudentChallenge.model.Student;

import java.util.ArrayList;
import java.util.List;

public class QueryList <T extends Student & QueryItem> extends ArrayList<T> {

    //private List<T> items;

    public QueryList() {

    }

    public QueryList(List<T> items) {

        super(items);
        //this.items = items;
    }

    public QueryList<T> getMatches(String field, String value) {

        QueryList<T> matches = new QueryList<>();
        for (var item : this) {
            if (item.matchFieldValue(field, value)) {
                matches.add(item);
            }
        }
        return matches;
    }
}
