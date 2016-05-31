package com.graphhopper.api.vrp.client.model;

import com.graphhopper.api.vrp.client.StringUtil;
import java.util.*;



import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.JsonProperty;


@ApiModel(description = "")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2015-10-19T11:09:20.969+02:00")
public class Relation   {
  
  private String type = null;
  private List<String> ids = new ArrayList<String>();

  
  /**
   * identifier of relation
   **/
  @ApiModelProperty(value = "identifier of relation")
  @JsonProperty("type")
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }

  
  /**
   * An array of ids that should be related
   **/
  @ApiModelProperty(value = "An array of ids that should be related")
  @JsonProperty("ids")
  public List<String> getIds() {
    return ids;
  }
  public void setIds(List<String> ids) {
    this.ids = ids;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Relation {\n");
    
    sb.append("    type: ").append(StringUtil.toIndentedString(type)).append("\n");
    sb.append("    ids: ").append(StringUtil.toIndentedString(ids)).append("\n");
    sb.append("}");
    return sb.toString();
  }
}
