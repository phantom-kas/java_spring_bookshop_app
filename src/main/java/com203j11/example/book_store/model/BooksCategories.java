package com203j11.example.book_store.model;

public class BooksCategories {
  private Integer id;
  private Integer categoryId;
  private Integer bookId;



  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getCategoryId() {
    return this.categoryId;
  }

  public void setCategoryId(Integer categoryId) {
    this.categoryId = categoryId;
  }

  public Integer getBookId() {
    return this.bookId;
  }

  public void setBookId(Integer bookId) {
    this.bookId = bookId;
  }


  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", categoryId='" + getCategoryId() + "'" +
      ", bookId='" + getBookId() + "'" +
      "}";
  }

}
