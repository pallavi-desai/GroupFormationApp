package CSCI5308.GroupFormationTool.GroupFormationAlgorithm;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SurveyScaleText extends SurveyScale {
	private static final Logger log = LoggerFactory.getLogger(SurveyScaleText.class);
	private static final String SURVEY_SCALE_TEXT = "text";

	@Override
	public double distance(ISurveyResponse rp1, ISurveyResponse rp2, int index) {
		String rpValue1 = rp1.getResponses().get(index);
		String rpValue2 = rp2.getResponses().get(index);
		double distance = 0;
		List<String> criteria;

		log.info("SurveyScale={}, action={}, status={}", 
				SURVEY_SCALE_TEXT, "Compute Distance", "Starting...");
		criteria = Arrays.asList(this.getCriteria().toLowerCase().split(","));
		for (String criterion : criteria) {
			switch (criterion) {
			case "similar":
				break;
			case "dissimilar":
				break;
			case "grtx":
				break;
			case "lessx":
				break;
			default:
			}
		}
		log.info("SurveyScale={}, action={}, status={}, value={}", 
				SURVEY_SCALE_TEXT, "Compute Distance", "Success", distance);
		return distance;
	}
}
