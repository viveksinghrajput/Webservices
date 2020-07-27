/*
*FinanceDOMapping.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Jul 26, 2018
*/
package com.rural.Model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"DONUMBER",
    "DOAMOUNT",
    "VALIDFROM",
    "VALIDTO"
})
public class FinanceDOTemp {

    @JsonProperty("DONUMBER")
    private String doNumber;
    
    @JsonProperty("DOAMOUNT")
    private String doAmount;
  
    @JsonProperty("VALIDFROM")
    private String validFrom;
    
    @JsonProperty("VALIDTO")
    private String validTo;
    
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public String getDoNumber() {
		return doNumber;
	}

	public void setDoNumber(String doNumber) {
		this.doNumber = doNumber;
	}

	public String getDoAmount() {
		return doAmount;
	}

	public void setDoAmount(String doAmount) {
		this.doAmount = doAmount;
	}

	public String getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(String validFrom) {
		this.validFrom = validFrom;
	}

	public String getValidTo() {
		return validTo;
	}

	public void setValidTo(String validTo) {
		this.validTo = validTo;
	}

	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}

	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}


}
