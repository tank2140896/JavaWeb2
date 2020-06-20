package ${packageName};

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ${className} {

<#list propertyList as pl>    
    private String ${pl};
    
</#list>
}