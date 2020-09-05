package CSCI5308.GroupFormationTool.GroupFormationAlgorithm;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SurveyScaleNumeric extends SurveyScale {
	private static final Logger log = LoggerFactory.getLogger(SurveyScaleNumeric.class);
	private static final String SURVEY_SCALE_NUMERIC = "numeric";

	@Override
	public double distance(ISurveyResponse rp1, ISurveyResponse rp2, int index) {
		int rpValue1 = convertValue(rp1.getResponses().get(index));
		int rpValue2 = convertValue(rp2.getResponses().get(index));
		double distance = 0;
		List<String> criteria;

		log.info("SurveyScale={}, action={}, status={}", 
				SURVEY_SCALE_NUMERIC, "Compute Distance", "Starting...");
		criteria = Arrays.asList(this.getCriteria().toLowerCase().split(","));
		for (String criterion : criteria) {
			switch (criterion) {
			case "similar":
				distance += (double) distanceSimilar(rpValue1, rpValue2);
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
				SURVEY_SCALE_NUMERIC, "Compute Distance", "Success", distance);
		return distance;
	}

	public static int convertValue(String rpString) {
		int rpValue = 0;

		try {
			rpValue = Integer.parseInt(rpString);
		} catch (NumberFormatException e) {
			log.error("SurveyScale={}, action={}, status={}", 
					SURVEY_SCALE_NUMERIC, "ConvertValue", "Fail");
			rpValue = 0;
		}
		return rpValue;
	}

	public static int distanceSimilar(int val1, int val2) {
		return Math.abs(val1 - val2);
	}
}
