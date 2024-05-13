public class MyTestingClass {
    private int id;
    private String name;

    public MyTestingClass(int id, String name) {
        this.id = id;
        this.name = name;
        }

    @Override
    public int hashCode() {
        if (name == null){
            return 0;
        }
        int hash = 19;
        hash = 31 * hash + id;
        return hash;
    }

    @Override
    public String toString(){
        return "MyTestingClass" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

