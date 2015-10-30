package reciter.algorithm.evidence.targetauthor.scopus.strategy;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import reciter.algorithm.evidence.targetauthor.AbstractTargetAuthorStrategy;
import reciter.model.article.ReCiterArticle;
import reciter.model.author.TargetAuthor;
import xmlparser.scopus.model.Affiliation;
import xmlparser.scopus.model.Author;
import xmlparser.scopus.model.ScopusArticle;

public class StringMatchingAffiliation extends AbstractTargetAuthorStrategy {

	@Override
	public double executeStrategy(ReCiterArticle reCiterArticle, TargetAuthor targetAuthor) {
		ScopusArticle scopusArticle = reCiterArticle.getScopusArticle();
		if (scopusArticle != null) {
			for (Author scopusAuthor : scopusArticle.getAuthors().values()) {
				if (StringUtils.equalsIgnoreCase(scopusAuthor.getSurname(), targetAuthor.getAuthorName().getLastName())) {
					Set<Integer> afidSet = scopusAuthor.getAfidSet();
					for (int afid : afidSet) {
						Affiliation scopusAffialition = scopusArticle.getAffiliationMap().get(afid);
						if (scopusAffialition != null) {
							String affilName = scopusAffialition.getAffilname();
							if (containsWeillCornell(affilName)) {
								return 1;
							}
						}
					}
				}
			}
		}
		return 0;
	}

	@Override
	public double executeStrategy(List<ReCiterArticle> reCiterArticles, TargetAuthor targetAuthor) {
		int sum = 0;
		for (ReCiterArticle reCiterArticle : reCiterArticles) {
			sum += executeStrategy(reCiterArticle, targetAuthor);
		}
		return sum;
	}
}