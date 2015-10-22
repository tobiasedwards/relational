import java.util.HashSet;
import java.util.ArrayList;
import java.util.HashMap;

public class Relation {

  private ArrayList<String> fields;
  private HashSet<Tuple> set;

  public Relation(ArrayList<String> fields)
  throws DuplicateFieldException {
    initFields(fields);
    set = new HashSet<Tuple>();
  }

  private void initFields(ArrayList<String> fields)
  throws DuplicateFieldException {
    this.fields = new ArrayList<String>();

    for (String field : fields) {
      if (this.fields.contains(field)) {
        String exceptionString =
          "Duplicate field name while creating Relation fields";
        throw new DuplicateFieldException(exceptionString);
      }

      this.fields.add(field);
    }
  }

  public void insert(Tuple tuple)
  throws UnexpectedFieldException {
    ArrayList<String> tupleFields = tuple.getFields();
    if (!fields.containsAll(tuple.getFields())) {
      String exceptionString =
        "Unexpected fields in Relation insert";
      tupleFields.removeAll(fields);
      throw new UnexpectedFieldException(exceptionString, tupleFields);
    }

    Tuple newTuple = addNullElements(tuple);

    set.add(newTuple);
  }

  // TODO: Put this in Tuple class?
  private Tuple addNullElements(Tuple tuple) {
    ArrayList<String> missingFields = (ArrayList<String>) fields.clone();
    missingFields.removeAll(tuple.getFields());

    HashMap<String, DataType> newTupleMap = tuple.getMap();
    for (String field : missingFields) {
      newTupleMap.put(field, null);
    }

    Tuple newTuple;
    try {
      newTuple = new Tuple(newTupleMap);
    } catch (DuplicateFieldException e) {
      // Should never get to this point
      System.out.println(e.getMessage());
      System.exit(1);
      return null;
    }

    return newTuple;
  }

  public String toString() {
    String repr = "Relation[fields=" + fields.toString() + " tuples=[";

    boolean firstIteration = true;
    for (Tuple tuple : set) {
      if (firstIteration) {
        firstIteration = false;
      } else {
        repr += ", ";
      }

      repr += tuple;
    }

    repr += "]]";

    return repr;
  }

}
