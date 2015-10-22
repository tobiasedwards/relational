public class DataType {

  private Object value;

  public DataType(Object value) {
    this.value = value;
  }

  public String toString() {
    if (value == null) {
      return "DataType[null]";
    }

    return "DataType[" + value.toString() + "]";
  }

}
