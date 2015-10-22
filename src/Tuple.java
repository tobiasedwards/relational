import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;

public class Tuple {

  private ArrayList<String> fields;
  private HashMap<String, DataType> map;

  public Tuple(Map<String, DataType> map)
  throws DuplicateFieldException {
    initFields(map.keySet());
    initMap(map);
  }

  private void initFields(Set<String> mapKeys)
  throws DuplicateFieldException {
    fields = new ArrayList<String>();

    for (String field : mapKeys) {
      if (fields.contains(field)) {
        String exceptionString =
          "Duplicate field name while creating Tuple fields";
        throw new DuplicateFieldException(exceptionString);
      }

      fields.add(field);
    }
  }

  private void initMap(Map<String, DataType> map) {
    this.map = new HashMap<String, DataType>();
    this.map.putAll(map);
  }

  public ArrayList<String> getFields() {
    if (fields == null) {
      return null;
    }

    return (ArrayList<String>) fields.clone();
  }

  public HashMap<String, DataType> getMap() {
    if (map == null) {
      return null;
    }

    return (HashMap<String, DataType>) map.clone();
  }

  public String toString() {
    String repr = "Tuple[";

    boolean firstIteration = true;
    for (Map.Entry<String, DataType> entry : map.entrySet()) {
      if (firstIteration) {
        firstIteration = false;
      } else {
        repr += ", ";
      }

      repr += entry.getKey() + "=" + entry.getValue();
    }

    repr += "]";

    return repr;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof Tuple)) {
      return false;
    }
    Tuple tuple = (Tuple) obj;
    return (this.map == tuple.getMap());
  }

}
