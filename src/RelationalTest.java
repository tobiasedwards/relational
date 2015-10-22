import java.util.*;

public class RelationalTest {

  public static void main(String[] args)
  throws Exception {
    ArrayList<String> fields = new ArrayList<String>();
    fields.add("name");
    fields.add("gpa");
    fields.add("student_num");

    Relation students = new Relation(fields);

    System.out.println(students);

    HashMap<String, DataType> map = new HashMap<String, DataType>();
    map.put("name", new DataType("Tom Duke"));
    map.put("student_num", new DataType("708842"));
    Tuple tom = new Tuple(map);

    System.out.println(tom);

    students.insert(tom);

    System.out.println(students);
  }

}
