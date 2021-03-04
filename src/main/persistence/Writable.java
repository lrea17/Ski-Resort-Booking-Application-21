package persistence;

import org.json.JSONObject;

/**
 This class has been copied from JSonSerializationDemo
 Repo: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
 */

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
