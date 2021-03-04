package ski.reservation.application.model;

import org.json.JSONObject;
import persistence.Writable;

public class HistoricalChanges implements Writable {
    private String name;
    private Category category;

    // EFFECTS: constructs a thingy with a name and category
    public HistoricalChanges(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    // EFFECTS: returns string representation of this thingy
    public String toString() {
        return category + ": " + name;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("category", category);
        return json;
    }
}
