package co.com.reto.dynamodb;

import co.com.reto.dynamodb.helper.TemplateAdapterOperations;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class DynamoDBTemplateAdapter extends TemplateAdapterOperations<Object /*domain model*/, String, ModelEntity /*adapter model*/> /* implements Gateway from domain */ {

    public DynamoDBTemplateAdapter(AmazonDynamoDB connectionFactory, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(connectionFactory, mapper, d -> mapper.map(d, Object.class /*domain model*/));
    }

        public List<Object /*domain model*/> getEntityBySomeKey(String key) {
            DynamoDBQueryExpression<ModelEntity /*adapter model*/> queryExpression = generateQueryExpression(key);
            return query(queryExpression);
        }

        private DynamoDBQueryExpression<ModelEntity /*adapter model*/> generateQueryExpression(String key) {
            Map<String, AttributeValue> eav = new HashMap<>();
            eav.put(":val1", new AttributeValue().withS(key));

            return new DynamoDBQueryExpression<ModelEntity /*adapter model*/>()
                    .withKeyConditionExpression("someKey = :val1")
                    .withExpressionAttributeValues(eav);
        }
}
