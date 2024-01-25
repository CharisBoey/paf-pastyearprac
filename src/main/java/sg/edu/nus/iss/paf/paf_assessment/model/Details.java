package sg.edu.nus.iss.paf.paf_assessment.model;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Details {
    private String _id;
    private String description;
    private String address;
    private String image;
    private Double price;
    private String amenities;

    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public String getAmenities() {
        return amenities;
    }
    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    public Details(String _id, String description, String address, String image, Double price, String amenities) {
        this._id = _id;
        this.description = description;
        this.address = address;
        this.image = image;
        this.price = price;
        this.amenities = amenities;
    }
    public Details() {
    }

    @Override
    public String toString() {
        return "Details [_id=" + _id + ", description=" + description + ", address=" + address + ", image=" + image
                + ", price=" + price + ", amenities=" + amenities + "]";
    }

    public JsonObject objToJSON(){
        return Json.createObjectBuilder()
            .add("_id", get_id())
            .add("description", getDescription())
            .add("address", getAddress().toString())
            .add("image", getImage())
            .add("price", getPrice())
            .add("amenities", getAmenities())
            .build();
    }

    public static Details JSONToObj(Document jsonObject){
        Details details = new Details();
        details.set_id(jsonObject.getString("_id"));
        details.setDescription(jsonObject.getString("description"));
        
        //address HAVE TO BE REFORMATTED *** NESTED VALUE
        Document d = jsonObject.get("address", Document.class);
        StringBuilder str = new StringBuilder();
        str.append(d.getString("street")).append(d.getString("suburb")).append(d.getString("country"));
        String addString = str.toString();
        details.setAddress(addString);

        //image HAVE TO BE REFORMATTED *** NESTED VALUE
        Document imgJsonObject = (Document) jsonObject.get("images");
        details.setImage(imgJsonObject.getString("picture_url"));
        
        details.setPrice(jsonObject.getDouble("price"));

        //amenities is in an ArrayList
        List<String> amenitiesList = new ArrayList<>();
        amenitiesList = jsonObject.getList("amenities", String.class);
        
        StringBuilder str2 = new StringBuilder();
        for (String e: amenitiesList){
            str2.append(e.toString());
            str2.append(", ");
        }
        String amenitiesStr = str2.toString();
        details.setAmenities(amenitiesStr);

        return details;
    }

}
