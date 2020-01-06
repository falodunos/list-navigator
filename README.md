# List Navigator

**REQUEST PARAMETERS**:
```
{ "pageSize": 5, "pageNumber": 1}
```

**DATA SOURCE**
```
The data source is a property file ListNavigatorConfig.properties. This can be change 
to any preffered data source that returns a comma separated string value. 

Our use case is: item1~item2~item3~item4~item5~item6~item7~item8~item9~item10~item11~item12~item13~item14~item15~item16~item17~item18
```
**SAMPLE RESPONSE**:
```
1. item1 ~2. item2 ~3. item3 ~4. item4  ~5. Next
```

**HOW TO RUN**
```
The main method is in ItemsHandler.java file. Run this file and call the following method:

public String navigate(JsonObject req) {} 

You can also create a jar file of this application and use it as dependencies to your application.
```