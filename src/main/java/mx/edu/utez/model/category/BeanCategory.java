package mx.edu.utez.model.category;

public class BeanCategory {
    private int idCategory;
    private String name;
    private int status;

    public BeanCategory() {
    }

    public BeanCategory(int idCategory, String name, int status) {
        this.idCategory = idCategory;
        this.name = name;
        this.status = status;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
