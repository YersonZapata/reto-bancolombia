package co.com.reto.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;

@DynamoDBTable(tableName = "table_name")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModelEntity {
    @DynamoDBHashKey(attributeName = "key")
    String id;
    @DynamoDBAttribute(attributeName = "atr1")
    String atr1;
}