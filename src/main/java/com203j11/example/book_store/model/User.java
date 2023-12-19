package com203j11.example.book_store.model;

public class User {
  private int id;
	private String username;
	private String password;
	private int role;


  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getRole() {
    return this.role;
  }

  public void setRole(int role) {
    this.role = role;
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", username='" + getUsername() + "'" +
      ", password='" + getPassword() + "'" +
      ", role='" + getRole() + "'" +
      "}";
  }

}
