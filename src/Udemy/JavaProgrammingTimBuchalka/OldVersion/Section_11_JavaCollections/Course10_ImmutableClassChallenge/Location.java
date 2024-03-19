package CourseCodes.OldSections.Section_11_JavaCollections.Course10_ImmutableClassChallenge;

import java.util.HashMap;
import java.util.Map;

public class Location {
    private final int locationID;
    private final String description;
    private final Map<String, Integer> exits;

    public Location(int locationID, String description, Map<String, Integer> exits) {
        this.locationID = locationID;
        this.description = description;
        if(exits != null) {
            this.exits = new HashMap<>(exits);      // This gives NullPointException error at run time when we pass exits = null
        } else {                                    // That's why we used here if-else statement for appropriate input to <>
            this.exits = new HashMap<>();
        }
        this.exits.put("Q", 0);
    }
    public int getLocationID() {
        return locationID;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Integer> getExits() {
        return new HashMap<>(exits);
    }
}
