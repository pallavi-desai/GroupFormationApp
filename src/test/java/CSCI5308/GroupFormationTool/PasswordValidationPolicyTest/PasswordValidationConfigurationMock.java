package CSCI5308.GroupFormationTool.PasswordValidationPolicyTest;

import java.util.HashMap;
import java.util.Map;

import CSCI5308.GroupFormationTool.PasswordValidationPolicy.IPasswordValidationConfiguration;

public class PasswordValidationConfigurationMock implements IPasswordValidationConfiguration {
	
	private static final String MIN_LENGTH = "min_length";
	private static final String MAX_LENGTH = "max_length";
	private static final String MIN_LOWERCASE = "min_lowercase";
	private static final String MIN_UPPERCASE = "min_uppercase";
	private static final String MIN_NON_ALPHANUM = "min_non_alphanum";
	private static final String FORBIDDEN_CHARSET = "forbidden_charset";
	private static final String HISTORY_CONSTRAINT = "history_constraint";
	
	@Override
	public String getConfig(String configKey) throws IllegalArgumentException {
		Map<String, String> configMap = new HashMap<String, String>();
		
		configMap.put(MIN_LENGTH, "8");
		configMap.put(MAX_LENGTH, "12");
		configMap.put(MIN_LOWERCASE, "2");
		configMap.put(MIN_UPPERCASE, "2");
		configMap.put(MIN_NON_ALPHANUM, "2");
		configMap.put(FORBIDDEN_CHARSET, "\\&\"'");
		configMap.put(HISTORY_CONSTRAINT, "0");
		
		if (null == configKey)
			throw new IllegalArgumentException("Null Key");
		
		if (configMap.containsKey(configKey))
			return configMap.get(configKey);
		else
			return null;
	}
}
