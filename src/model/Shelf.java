package model;

public class Shelf {
    private Integer idShelf;
    private Boolean isRefrigirated;

    public Shelf(Integer idShelf, Boolean isRefrigirated) {
        setIdShelf(idShelf);
        setRefrigirated(isRefrigirated);
    }

    public void setIdShelf(Integer idShelf) {
        this.idShelf = idShelf;
    }

    public void setRefrigirated(Boolean refrigirated) {
        isRefrigirated = refrigirated;
    }

    public Integer getIdShelf() {
        return idShelf;
    }

    public Boolean getRefrigirated() {
        return isRefrigirated;
    }

    public String toString(){
        return idShelf + (isRefrigirated ? " Réfrigére" : " Non réfrigéré");
    }
}
