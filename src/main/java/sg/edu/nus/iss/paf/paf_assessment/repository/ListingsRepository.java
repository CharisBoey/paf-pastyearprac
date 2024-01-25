package sg.edu.nus.iss.paf.paf_assessment.repository;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ListingsRepository {

    @Autowired
    MongoTemplate mongoTemplate;
    
    public List<String> returnAllDistinctCountries(){
        //db.listings.distinct("address.country")

        List<String> distinctCountries = mongoTemplate.findDistinct(new Query(), "address.country", "listings", String.class);

        return distinctCountries;
    }

    public List<Document> returnAllMatched(String country, Integer noOfPerson, Float priceRangeMin, Float priceRangeMax){
        /* db.listings.aggregate([
            {
                $match: {
                "address.country": "Australia"
                }
            },
            {
                $match: {
                    accommodates: 2
                }
            },
            {
                $match: {
                    price: { $gte: 1, $lte: 50 }
                }
            },
            
        ]) */

        MatchOperation matchResults = Aggregation.match(Criteria.where("address.country").is(country).and("accommodates").is(noOfPerson).and("price").gte(priceRangeMin).lte(priceRangeMax));

        SortOperation sortOperation = Aggregation.sort(Sort.by(Direction.DESC,"price"));

        Aggregation pipeline = Aggregation.newAggregation(matchResults, sortOperation);
        AggregationResults<Document> results = mongoTemplate.aggregate(pipeline,"listings",Document.class);
        
        List<Document> resultList = results.getMappedResults();
        System.out.println("!!!Find: " + resultList.toString() + "\n");
        return resultList;
    }

    public Document returnMatched(String _id){
        /* db.listings.find({
            _id:'16231922'
       }) */
       
        Criteria criteria = Criteria.where("_id").is(_id);
        Query query = Query.query(criteria);
        List<Document> result = mongoTemplate.find(query, Document.class,"listings");
        Document resultMatch = result.get(0);
       
        return resultMatch;
    }

    
}
