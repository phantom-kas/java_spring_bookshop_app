package com203j11.example.book_store.model;




public class Category {
   
    private Integer id;
    private String category;

    // Getters and setters

    public Integer getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", category='" + getCategory() + "'" +
            "}";
    }


}