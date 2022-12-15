package day07;

public class App {
  private String name;
  private String category;
  private Float rating = 0f;
  
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getCategory() {
    return category;
  }
  public void setCategory(String category) {
    this.category = category;
  }
  public Float getRating() {
    return rating;
  }
  public void setRating(Float rating) {
    this.rating = rating;
  }

  //can use source action to generate to.String
  //must be override cos it's overriding parent toString function.
  @Override
  public String toString(){
    return "App [name =" + name + ", category =" + category + ", rating =" + rating +"]";
  }
  

  
}
