package persistence;

import org.json.JSONObject;

/**
 * Citation: code for this class has been obtained from JSonSerializationDemo
 * URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
 */

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
