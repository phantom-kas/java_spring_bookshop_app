package com203j11.example.book_store.model;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Book {
private Integer id;
private String bookName;
private String summary;
private String picture;

private BigDecimal price;
private Integer category;
private String author;
private LocalDateTime createdAt;


  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getBookName() {
    return this.bookName;
  }

  public void setBookName(String bookName) {
    this.bookName = bookName;
  }

  public String getSummary() {
    return this.summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public String getPicture() {
    return this.picture;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }

  public BigDecimal getPrice() {
    return this.price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Integer getCategory() {
    return this.category;
  }

  public void setCategory(Integer category) {
    this.category = category;
  }

  public String getAuthor() {
    return this.author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public LocalDateTime getCreatedAt() {
    return this.createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", bookName='" + getBookName() + "'" +
      ", summary='" + getSummary() + "'" +
      ", picture='" + getPicture() + "'" +
      ", price='" + getPrice() + "'" +
      ", category='" + getCategory() + "'" +
      ", author='" + getAuthor() + "'" +
      ", createdAt='" + getCreatedAt() + "'" +
      "}";
  }

  public void setPrice(String string) {
  }
  

}
