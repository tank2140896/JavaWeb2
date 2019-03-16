<#if imports != "">
<#list imports?split(",") as importOut>
${importOut}
</#list>

</#if>
public class ${className} {

<#list jsonList as jl>
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