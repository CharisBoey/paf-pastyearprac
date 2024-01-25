package sg.edu.nus.iss.paf.paf_assessment.model;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class DisplayListing {
    private String _id;
    private String name;
    private Double price;
    //images.picture_url
    private String image;
    
    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    
    public DisplayListing() {
    }
    public DisplayListing(String _id, String name, Double price, String image) {
        this._id = _id;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    @Override
    public String toString() {
        return "DisplayListing [_id=" + _id + ", name=" + name + ", price=" + price + ", image=" + image + "]";
    }
    

    public JsonObject objToJSON(){
        return Json.createObjectBuilder()
            .add("_id", get_id())
            .add("name", getName())
            .add("price", getPrice())
            .add("image", getImage())
            .build();
    }

    public static DisplayListing JSONToObj(Document jsonObject){
        DisplayListing dispList = new DisplayListing();
        dispList.set_id(jsonObject.getString("_id"));
        dispList.setName(jsonObject.getString("name"));
        dispList.setPrice(jsonObject.getDouble("price"));
      
        //IMAGES HAVE TO BE REFORMATTED *** NESTED VALUE
        Document jsonObject2 = (Document) jsonObject.get("images");
        dispList.setImage(jsonObject2.getString("picture_url"));
        
        return dispList;
    }
}
