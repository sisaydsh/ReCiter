package reciter.engine.analysis.evidence;

import lombok.Data;
import lombok.ToString;
import reciter.model.identity.OrganizationalUnit;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@Data
@ToString
@DynamoDBDocument
public class AffiliationEvidence {
    private List<String> institutionalAffiliations;
    private List<String> emails;
    private List<OrganizationalUnit> departments;
    private String articleAffiliation;
}
