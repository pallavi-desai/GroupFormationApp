package CSCI5308.GroupFormationTool.GroupFormationAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SurveyScaleMCQ2 extends SurveyScale {
	private static final Logger log = LoggerFactory.getLogger(SurveyScaleMCQ2.class);
	private static final String SURVEY_SCALE_MCQ2 = "MCQ2";
	
	@Override
	public double distance(ISurveyResponse rp1, ISurveyResponse rp2, int index) {
		int rpValue1 = convertValue(rp1.getResponses().get(index));
		int rpValue2 = convertValue(rp2.getResponses().get(index));
		double distance = 0;
		int msb;
		List<String> criteria;

		try {
			msb = Integer.parseInt(this.getOptionscount());
		} catch (NumberFormatException e) {
			log.error("SurveyScale={}, action={}, status={}", 
					SURVEY_SCALE_MCQ2, "ConvertValue", "Fail");
			msb = 0;
		}
		
		log.info("SurveyScale={}, action={}, status={}", 
				SURVEY_SCALE_MCQ2, "Compute Distance", "Starting...");
		criteria = Arrays.asList(this.getCriteria().toLowerCase().split(","));
		for (String criterion : criteria) {
			switch (criterion) {
			case "similar":
				distance += (double) distanceSimilar(rpValue1, rpValue2);
				break;
			case "dissimilar":
				distance += (double) distanceDissimilar(rpValue1, rpValue2, msb);
				break;
			case "grtx":
				break;
			case "lessx":
				break;
			default:
			}
		}

		log.info("SurveyScale={}, action={}, status={}, value={}", 
				SURVEY_SCALE_MCQ2, "Compute Distance", "Success", distance);
		return distance;
	}

	public static int convertValue(String rpString) {
		List<String> strRpValues = Arrays.asList(rpString.split(","));
		List<Integer> intRpValues = new ArrayList<Integer>();

		for (String strRpValue : strRpValues) {
			int value = 0;
			try {
				value = Integer.parseInt(strRpValue.trim());
			} catch (NumberFormatException e) {
				log.error("SurveyScale={}, action={}, status={}", 
						SURVEY_SCALE_MCQ2, "ConvertValue", "Fail");
				value = 0;
			}
			intRpValues.add(1 << (value - 1));
		}

		int finalValue = 0;
		for (Integer rpValue : intRpValues) {
			finalValue |= rpValue;
		}

		return finalValue;
	}

	public static int distanceSimilar(int val1, int val2) {
		int distance = 0;
		int bitVal1 = val1;
		int bitVal2 = val2;

		distance = countSetBits(bitVal1 ^ bitVal2);
		return distance;
	}

	public static int distanceDissimilar(int val1, int val2, int msb) {
		int distance = 0;
		int bitVal1 = val1;
		int bitVal2 = val2;

		distance = countSetBits((~(bitVal1 ^ bitVal2) & getMask(msb)));
		return distance;
	}

	public static int countSetBits(int n) {
		int count = 0;
		while (n > 0) {
			count += n & 1;
			n >>= 1;
		}
		return count;
	}

	public static int getMask(int MSB) {
		int mask = 0;
		for (int i = 0; i < MSB; i++) {
			mask <<= 1;
			mask |= 1;
		}
		return mask;
	}
}
