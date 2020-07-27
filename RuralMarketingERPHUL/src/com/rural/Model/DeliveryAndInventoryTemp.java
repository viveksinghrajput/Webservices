/*
*DeliveryAndInventoryTemp.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Jul 4, 2018
*/
package com.rural.Model;



import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"user",
    "no_of_kits_received",
    "no_of_kits_dispatched_logi",
    "logi_remarks"
})
public class DeliveryAndInventoryTemp {

    @JsonProperty("user")
    private String user;
    
    @JsonProperty("no_of_kits_received")
    private String no_of_kits_received;
    
    @JsonProperty("no_of_kits_dispatched_logi")
    private String no_of_kits_dispatched_logi;

    @JsonProperty("logi_remarks")
    private String logi_remarks;
    
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getNo_of_kits_received() {
		return no_of_kits_received;
	}

	public void setNo_of_kits_received(String no_of_kits_received) {
		this.no_of_kits_received = no_of_kits_received;
	}

	public String getNo_of_kits_dispatched_logi() {
		return no_of_kits_dispatched_logi;
	}

	public void setNo_of_kits_dispatched_logi(String no_of_kits_dispatched_logi) {
		this.no_of_kits_dispatched_logi = no_of_kits_dispatched_logi;
	}

	public String getLogi_remarks() {
		return logi_remarks;
	}

	public void setLogi_remarks(String logi_remarks) {
		this.logi_remarks = logi_remarks;
	}

	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}

	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}
    
   

   

}
