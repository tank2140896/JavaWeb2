<#if imports != "">
<#list imports?split(",") as importOut>
${importOut}
</#list>

</#if>
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ${className} {

<#list jsonList as jl>
	@JsonProperty(value="${jl.attribute}")
    private ${jl.javaType} ${jl.attributeLowerCase};
    
</#list>
<#list jsonList as jl>
    public void set${jl.attributeUpperCase}(${jl.javaType} ${jl.attributeLowerCase}) {
        this.${jl.attributeLowerCase} = ${jl.attributeLowerCase};
    }
     
    public ${jl.javaType} get${jl.attributeUpperCase}() {
    	return ${jl.attributeLowerCase};
    }

</#list>
}
