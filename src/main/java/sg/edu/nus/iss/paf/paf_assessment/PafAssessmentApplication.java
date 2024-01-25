package sg.edu.nus.iss.paf.paf_assessment;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import sg.edu.nus.iss.paf.paf_assessment.repository.queries;

@SpringBootApplication
public class PafAssessmentApplication implements CommandLineRunner{

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
    private JdbcTemplate template;

	
	public static void main(String[] args) {
		SpringApplication.run(PafAssessmentApplication.class, args);
	}

	@Override
	public void run(String... args)throws Exception{
	
		/* List<String> distinctCountries = mongoTemplate.findDistinct(new Query(), "address.country", "listings", String.class);
		
		System.out.println("DISTINCT COUNTRIES !!! " + distinctCountries.toString()); */


		/* MatchOperation matchResults = Aggregation.match(Criteria.where("address.country").is("Australia").and("accommodates").is(2).and("price").gte(1).lte(100));
        Aggregation pipeline = Aggregation.newAggregation(matchResults);
        AggregationResults<Document> results = mongoTemplate.aggregate(pipeline,"listings",Document.class);
		List<Document> resultList = results.getMappedResults();

        System.out.println("!!!!!!!!!!!!!: " + resultList.toString() + "\n"); */

		/* SqlRowSet rs = template.queryForRowSet(queries.SQL_Find_Vacancy,10006546);
		
		int resultInt = 0;

        while (rs.next()){
            resultInt = rs.getInt("vacancy");
        } */
		
		//System.out.println("**********" + resultInt);

	}
}
